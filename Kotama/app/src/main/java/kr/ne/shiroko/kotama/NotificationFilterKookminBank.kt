package kr.ne.shiroko.kotama

class NotificationFilterKookminBank : NotificationFilter {
    companion object {
        const val packageName = "com.kbstar.kbbank"
    }

    override fun predicate(data: MyNotification): Boolean {
        if (data.title == null) return false
        if (data.title == "") return false

        return true
    }
}
