package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class NotificationFilterNaverTest {
    private val subject = NotificationFilterNaver()
    private val packageName =NotificationFilterNaver.packageName

    @Test
    fun blank() {
        /*
        {
            "id": 1596032129,
            "key": "0|com.nhn.android.search|1596032129|null|10407",
            "postTime": 1708854549040,
            "packageName": "com.nhn.android.search",
            "extras": {}
        }
         */

        val notification = MyNotification(packageName)
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun real() {
        /*
        {
            "id": -123456789,
            "key": "0|com.nhn.android.search|-123456789|null|12345",
            "postTime": 1708854549059,
            "packageName": "com.nhn.android.search",
            "extras": {
                "title": "네이버 페이",
                "text": "(주)어쩌고마트에서 25,750원 결제되었습니다. (결제수단: 카드 간편결제)",
                "bigText": "(주)어쩌고마트에서 25,750원 결제되었습니다. (결제수단: 카드 간편결제)",
                "summaryText": "네이버 페이"
            }
        }
         */
        val notification = MyNotification(packageName)
        notification.title = "네이버 페이"
        notification.text = "(주)어쩌고마트에서 25,750원 결제되었습니다. (결제수단: 카드 간편결제)"
        notification.bigText= "(주)어쩌고마트에서 25,750원 결제되었습니다. (결제수단: 카드 간편결제)"
        notification.summaryText = "네이버 페이"
        val actual = subject.predicate(notification)
        Assert.assertEquals(true, actual)
    }
}
