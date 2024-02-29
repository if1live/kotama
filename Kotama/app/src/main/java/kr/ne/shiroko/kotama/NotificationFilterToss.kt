package kr.ne.shiroko.kotama

class NotificationFilterToss : NotificationFilter {
    companion object {
        const val packageName = "viva.republica.toss"
    }

    override fun predicate(data: MyNotification): Boolean {
        val title = data.title
        if (title == null) return false
        if (title == "") return false
        if (title == "근처에 토스를 켠 사람이 있어요!") return false
        if (title == "한 번에 많이 들어온 금액 발견") return false

        val text = data.text
        if (text == "사라지기 전에 토스를 켜보세요.") return false

        val subText = data.subText
        if (subText == "만보기") return false

        return true
    }
}
