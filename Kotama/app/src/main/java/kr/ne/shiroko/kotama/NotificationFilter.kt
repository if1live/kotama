package kr.ne.shiroko.kotama

class NotificationFilter {
    companion object {
        val packageNames = listOf(
            // 토스
            "viva.republica.toss",
            // Samsung Wallet (Samsung Pay)
            "com.samsung.android.spay",
            // 네이버 - NAVER
            "com.nhn.android.search",
            // KB국민은행 스타뱅킹
            "com.kbstar.kbbank",
            // 케이뱅크 (Kbank) - make money
            "com.kbankwith.smartbank"
        )

        // 부분 일치
        val bannedPartialTexts = listOf<String>(
            // 일반
            "(광고)",
            "신용점수",

            // 네이버페이
            // [현장결제]포인트뽑기 혜택의 포인트 7원이 적립되었어요.
            "[현장결제]포인트뽑기",

            // "매달 내는 돈이에요." 알림은 여러줄로 구성된다.
            "매달 내는 돈이에요.",

            // 토스
            // 친구가 돈을 받아서 8원 모았어요
            "친구가 돈을 받아서",
            // 확인하지 않은 공지 1건
            "확인하지 않은 공지",
        )

        // 전체 일치
        val bannedFullTexts = listOf<String>(
            // 토스
            "근처에 토스를 켠 사람이 있어요!",
            "한 번에 많이 들어온 금액 발견",
            "사라지기 전에 토스를 켜보세요.",
            "만보기",

            // 삼성페이
            "DroidX 실행 중",
            "Samsung Pay 보호 중입니다",

            // 케이뱅크
            "띵동♪ 이번 달 이자가 도착했어요.",
            "구독료 돌려받기 이벤트 알림",
        )
    }


    private val checkTitleEmpty = ifTitleEmpty()
    private val checkPackageName = ifPackageNameInAllowlist(packageNames)
    private val checkBannedPartialText = ifMessageContainsDenylist(bannedPartialTexts)
    private val checkBannedFullText = ifMessageEqualsDenylist(bannedFullTexts)

    fun predicate(data: MyNotification): Boolean {
        if (!checkPackageName(data)) return false

        if (checkTitleEmpty(data)) return false
        if (checkBannedFullText(data)) return false
        if (checkBannedPartialText(data)) return false

        return true
    }
}

fun ifTitleEmpty(): (MyNotification) -> Boolean = { data ->
    when (data.title) {
        null -> true
        "" -> true
        else -> false
    }
}

fun ifPackageNameMatch(packageName: String): (MyNotification) -> Boolean = { data ->
    data.packageName == packageName
}

fun ifPackageNameInAllowlist(packageNames: List<String>): (MyNotification) -> Boolean = { data ->
    try {
        val found = packageNames.first { ifPackageNameMatch(it)(data) }
        true
    } catch (e: NoSuchElementException) {
        false
    }
}

fun ifMessagePredicate(s: String, fn: (String, String) -> Boolean): (MyNotification) -> Boolean =
    { data ->
        val title = data.title ?: ""
        val text = data.text ?: ""
        val bigText = data.bigText ?: ""
        val infoText = data.infoText ?: ""
        val subText = data.subText ?: ""
        val summaryText = data.summaryText ?: ""

        var result = false
        result = result || fn(title, s)
        result = result || fn(text, s)
        result = result || fn(bigText, s)
        result = result || fn(infoText, s)
        result = result || fn(subText, s)
        result = result || fn(summaryText, s)

        result
    }

fun ifMessageContains(s: String): (MyNotification) -> Boolean = { data ->
    val fn: (String, String) -> Boolean = { line, s -> line.contains(s) }
    ifMessagePredicate(s, fn)(data)
}

fun ifMessageEquals(s: String): (MyNotification) -> Boolean = { data ->
    val fn: (String, String) -> Boolean = { line, s -> line == s }
    ifMessagePredicate(s, fn)(data)
}

fun ifMessageContainsDenylist(items: List<String>): (MyNotification) -> Boolean = { data ->
    try {
        val found = items.first { ifMessageContains(it)(data) }
        true
    } catch (e: NoSuchElementException) {
        false
    }
}

fun ifMessageEqualsDenylist(items: List<String>): (MyNotification) -> Boolean = { data ->
    try {
        val found = items.first { ifMessageEquals(it)(data) }
        true
    } catch (e: NoSuchElementException) {
        false
    }
}
