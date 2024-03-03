package kr.ne.shiroko.kotama

class NotificationFilter {
    private val checkTitleEmpty = ifTitleEmpty()
    private val checkPackageName = ifPackageNameInAllowlist(packageNameAllowList)
    private val checkBannedPartialText = ifMessageContainsDenylist(bannedPartialTextList)
    private val checkBannedFullText = ifMessageEqualsDenylist(bannedFullTextList)

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
