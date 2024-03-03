package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class IfTitleEmptyTest {
    @Test
    fun blank() {
        val notification = MyNotification("com.sample");
        notification.title = ""

        val actual = ifTitleEmpty()(notification)
        Assert.assertEquals(true, actual)
    }

    @Test
    fun withNull() {
        val notification = MyNotification("com.sample");
        notification.title = null

        val actual = ifTitleEmpty()(notification)
        Assert.assertEquals(true, actual)
    }

    @Test
    fun plain() {
        val notification = MyNotification("com.sample");
        notification.title = "hello"

        val actual = ifTitleEmpty()(notification)
        Assert.assertEquals(false, actual)
    }
}

class IfPackageNameInAllowlistTest {
    private val packageNames = listOf("com.sample.foo", "com.sample.bar")
    private val fn = ifPackageNameInAllowlist(packageNames)

    @Test
    fun allowed() {
        val notification = MyNotification("com.sample.bar")
        Assert.assertEquals(true, fn(notification))
    }

    @Test
    fun denied() {
        val notification = MyNotification("com.sample.invalid")
        Assert.assertEquals(false, fn(notification))
    }
}

class IfMessageContainsDenylistTest {
    private val denylist = listOf("이벤트", "(광고)")
    private val predicate = ifMessageContainsDenylist(denylist)

    @Test
    fun contains() {
        val notification = MyNotification("com.sample");
        notification.infoText = "(광고) 현금영수증 자동 발급되는 간편결제 이용 약관에 동의해주세요."
        Assert.assertEquals(true, predicate(notification))
    }

    @Test
    fun notContains() {
        val notification = MyNotification("com.sample");
        notification.infoText = "대충 광고 아닌 메세지"
        Assert.assertEquals(false, predicate(notification))
    }
}
