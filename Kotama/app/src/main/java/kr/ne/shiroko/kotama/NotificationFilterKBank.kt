package kr.ne.shiroko.kotama

class NotificationFilterKBank : NotificationFilter {
    companion object {
        const val packageName = "com.kbankwith.smartbank"
    }

    override fun predicate(data: MyNotification): Boolean {
        if(data.title == "구독료 돌려받기 이벤트 알림") return false

        return true
    }
}
