package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class NotificationFilterRootTest {
    private val subject = NotificationFilterRoot()

    @Test
    fun denyList() {
        val notification = MyNotification("com.google.android.gm")
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun allowList() {
        val notification = MyNotification("com.nhn.android.search")
        notification.title = "네이버 페이"

        val actual = subject.predicate(notification)
        Assert.assertEquals(true, actual)
    }
}
