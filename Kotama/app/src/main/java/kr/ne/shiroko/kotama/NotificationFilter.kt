package kr.ne.shiroko.kotama

interface NotificationFilter {
    fun predicate(data: MyNotification): Boolean
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

fun ifMessageContains(s: String): (MyNotification) -> Boolean = { data ->
    val title = data.title ?: ""
    val text = data.text ?: ""
    val bigText = data.bigText ?: ""
    val infoText = data.infoText ?: ""
    val subText = data.subText ?: ""
    val summaryText = data.summaryText ?: ""

    val titleContains = title.contains(s)
    val textContains = text.contains(s)
    val bigTextContains = bigText.contains(s)
    val infoTextContains = infoText.contains(s)
    val subTextContains = subText.contains(s)
    val summaryTextContains = summaryText.contains(s)

    val contains =
        titleContains || textContains || bigTextContains || infoTextContains || subTextContains || summaryTextContains
    contains
}

fun ifMessageContainsDenylist(items: List<String>): (MyNotification) -> Boolean = { data ->
    try {
        val found = items.first { ifMessageContains(it)(data) }
        true
    } catch (e: NoSuchElementException) {
        false
    }
}
