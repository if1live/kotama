package kr.ne.shiroko.kotama

interface NotificationFilter {
    fun predicate(data: MyNotification): Boolean
}
