package kr.ne.shiroko.kotama

import org.junit.Assert
import org.junit.Test

class NotificationFilterTossTest {
    private val subject = NotificationFilterToss()
    private val packageName = NotificationFilterToss.packageName

    @Test
    fun blank() {
        /*
        {
            "id": 511393872,
            "key": "0|viva.republica.toss|511393872|null|10361",
            "postTime": 1708856514471,
            "packageName": "viva.republica.toss",
            "extras": {}
        }
        */
        val notification = MyNotification(packageName)
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun nearby() {
        /*
        {
            "id": 2106096057,
            "key": "0|viva.republica.toss|2106096057|null|10361",
            "postTime": 1708856514467,
            "packageName": "viva.republica.toss",
            "extras": {
                "title": "근처에 토스를 켠 사람이 있어요!",
                "text": "지금 눌러서 확인하기",
                "bigText": "지금 눌러서 확인하기"
            }
        }
        */
        val notification = MyNotification(packageName)
        notification.title = "근처에 토스를 켠 사람이 있어요!"
        notification.text = "지금 눌러서 확인하기"
        notification.bigText = "지금 눌러서 확인하기"
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun walking() {
        /*
        {
            "id": 300,
            "key": "0|viva.republica.toss|300|null|10361",
            "postTime": 1708851692313,
            "packageName": "viva.republica.toss",
            "extras": {
                "title": "846 걸음",
                "subText": "만보기"
            }
        }
        */
        val notification = MyNotification(packageName)
        notification.title = "846 걸음"
        notification.subText = "만보기"
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun disappear() {
        val notification  = MyNotification( packageName)
        notification.title = "강*해님 발견!"
        notification.text = "사라지기 전에 토스를 켜보세요."
        notification.bigText = "사라지기 전에 토스를 켜보세요."
        val actual = subject.predicate(notification)
        Assert.assertEquals(false, actual)
    }

    @Test
    fun real() {
        /*
        {
            "id": -537864431,
            "key": "0|viva.republica.toss|-537864431|null|10361",
            "postTime": 1708851913928,
            "packageName": "viva.republica.toss",
            "extras": {
                "title": "50,000원 입금",
                "text": "유저 → 내 토스뱅크 통장",
                "bigText": "유저 → 내 토스뱅크 통장",
                "subText": "토스뱅크"
            }
        }
         */
        val notification = MyNotification(packageName)
        notification.title = "50,000원 입금"
        notification.text = "유저 → 내 토스뱅크 통장"
        notification.bigText = "유저 → 내 토스뱅크 통장"
        notification.subText = "토스뱅크"
        val actual = subject.predicate(notification)
        Assert.assertEquals(true, actual)
    }
}
