package kr.ne.shiroko.kotama

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// https://devjaewoo.tistory.com/54
class MyNotificationListenerService : NotificationListenerService() {
    private val TAG = "MyNotificationListenerService"

    private val sender = MessageSender(webhookUrl)

    private val packageFilter = NotificationFilter()

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.e(TAG, "MyNotificationListener.onListenerConnected()")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.e(TAG, "MyNotificationListener.onListenerDisconnected()")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        if (sbn == null) {
            return
        }

        val data = MyNotification(sbn.packageName)
        data.fill(sbn)

        val required = packageFilter.predicate(data)
        if (!required) {
            return
        }

        val json = data.toJson()
        val jsonText = json.toString(2)
        Log.d(TAG, "onNotificationPosted:\n$jsonText")

        // kotlin 비동기 어떻게 쓰는거지?
        val myScope = CoroutineScope(Dispatchers.Default)
        myScope.launch {
            // 여기서 suspend 함수를 호출
            sender.sendMessage(json)
        }
    }
}
