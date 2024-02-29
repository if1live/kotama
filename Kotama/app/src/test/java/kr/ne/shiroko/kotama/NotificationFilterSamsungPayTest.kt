package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class NotificationFilterSamsungPayTest {
    private val subject = NotificationFilterSamsungPay()
    private val packageName = NotificationFilterSamsungPay.packageName

    @Test
    fun droidX() {
        /*
        {
            "id": 9999,
            "key": "0|com.samsung.android.spay|9999|null|10307",
            "postTime": 1708851642953,
            "packageName": "com.samsung.android.spay",
            "extras": {
                "title": "DroidX 실행 중",
                "text": "Samsung Pay 보호 중입니다"
            }
        }
         */
        val notification = MyNotification(packageName)
        notification.id = 9999
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun blank() {
        /*
        {
            "id": 0,
            "key": "0|com.samsung.android.spay|0|null|10307",
            "postTime": 1708851678348,
            "packageName": "com.samsung.android.spay",
            "extras": {}
        }
         */
        val notification = MyNotification(packageName)
        notification.id = 0
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun real() {
        /*
        {
            "id": 1471071348,
            "key": "0|com.samsung.android.spay|1471071348|null|10307",
            "postTime": 1708853559657,
            "packageName": "com.samsung.android.spay",
            "extras": {
                "title": "이디야커피(행신역점) ₩8,200",
                "text": "결제 내역 확인",
                "bigText": "결제 내역 확인"
            }
        }
         */
        val notification = MyNotification(packageName)
        notification.id = 1471071348
        notification.title = "이디야커피(행신역점) ₩8,200"
        notification.text = "결제 내역 확인"
        notification.bigText = "결제 내역 확인"
        val actual = subject.predicate(notification)
        Assert.assertEquals(true, actual)
    }
}
