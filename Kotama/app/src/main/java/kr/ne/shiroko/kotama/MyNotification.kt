package kr.ne.shiroko.kotama

import android.app.Notification
import android.os.Bundle
import android.service.notification.StatusBarNotification
import org.json.JSONObject

/**
 * android.os.Bundle를 유닛테스트에서 생성할 수 없다.
 * 적당히 필요한 정보만 넣어서 들고다니기
 */
data class MyNotification(val packageName: String) {
    var id: Int = 0
    var key: String = ""
    var postTime: Long = 0

    // extra
    var title: String? = null
    var text: String? = null
    var bigText: String? = null
    var infoText: String? = null
    var subText: String? = null
    var summaryText: String? = null

    fun fill(sbn: StatusBarNotification) {
        id = sbn.id
        key = sbn.key
        postTime = sbn.postTime

        val extras = sbn.notification.extras
        // https://developer.android.com/reference/android/app/Notification 필요하면 뒤져서 더 뜯기
        title = extras.getString(Notification.EXTRA_TITLE)
        text = extras.getString(Notification.EXTRA_TEXT)
        bigText = extras.getString(Notification.EXTRA_BIG_TEXT)
        infoText = extras.getString(Notification.EXTRA_INFO_TEXT)
        subText = extras.getString(Notification.EXTRA_SUB_TEXT)
        summaryText = extras.getString(Notification.EXTRA_SUMMARY_TEXT)
    }

    fun toJson(): JSONObject {
        val json = JSONObject()
        // StatusBarNotification
        json.put("id", id)
        json.put("key", key)
        json.put("postTime", postTime)
        json.put("packageName", packageName)

        // extras
        val jsonExtra = JSONObject()
        jsonExtra.put("title", title)
        jsonExtra.put("text", text)
        jsonExtra.put("bigText", bigText)
        jsonExtra.put("infoText", infoText)
        jsonExtra.put("subText", subText)
        jsonExtra.put("summaryText", summaryText)

        json.put("extras", jsonExtra)

        return json
    }
}
