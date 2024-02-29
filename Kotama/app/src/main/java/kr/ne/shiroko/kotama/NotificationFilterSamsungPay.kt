package kr.ne.shiroko.kotama

class NotificationFilterSamsungPay : NotificationFilter {
    companion object {
        const val packageName = "com.samsung.android.spay"
    }

    override fun predicate(data: MyNotification): Boolean {
        // 비어있는 데이터
        if (data.id == 0) return false

        // "DroidX 실행 중"
        if (data.id == 9999) return false

        return true
    }
}
