package kr.ne.shiroko.kotama

class NotificationFilterRoot : NotificationFilter {
    private val packageNameMap = mapOf(
        NotificationFilterNaver.packageName to NotificationFilterNaver(),
        NotificationFilterSamsungPay.packageName to NotificationFilterSamsungPay(),
        NotificationFilterToss.packageName to NotificationFilterToss(),
        NotificationFilterKookminBank.packageName to NotificationFilterKookminBank(),
        NotificationFilterKBank.packageName to NotificationFilterKBank(),
    )
    override fun predicate(data: MyNotification): Boolean {
        val packageName = data.packageName

        if (data.title == null) return false
        if (data.title == "") return false

        val packageFilter = packageNameMap[packageName]
        return packageFilter?.predicate(data) ?: false
    }
}
