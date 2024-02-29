package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class NotificationFilterKBankTest {
    private val subject = NotificationFilterKBank()
    private val packageName = NotificationFilterKBank.packageName

    @Test
    fun real() {
        /*
        {
            "id": 1303914982,
            "key": "0|com.kbankwith.smartbank|1303914982|gcm|10394",
            "postTime": 1708854546499,
            "packageName": "com.kbankwith.smartbank",
            "extras": {
                "title": "케이뱅크",
                "text": "승인 25,750원\n(주)대하마트\n카드(3390) | 02\/25 18:49 \n출금가능액 3,039,583원",
                "bigText": "승인 25,750원\n(주)대하마트\n카드(3390) | 02\/25 18:49 \n출금가능액 3,039,583원"
            }
        }
         */
        val notification = MyNotification(packageName)
        val actual = subject.predicate(notification)
        Assert.assertEquals(true, actual)
    }

    @Test
    fun cashback() {
        val notification = MyNotification(packageName)
        notification.title = "구독료 돌려받기 이벤트 알림"
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }
}
