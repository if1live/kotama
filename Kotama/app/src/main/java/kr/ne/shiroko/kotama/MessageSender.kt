package kr.ne.shiroko.kotama;

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

// https://soeun-87.tistory.com/23
class MessageSender(private val url: String) {
    private val TAG = "MyNotificationListenerService"

    private val client = OkHttpClient()

    suspend fun sendMessage(json: JSONObject) {
        // TODO: 더 멀쩡한 방법은
        val mediaType  = "application/json; charset=utf-8".toMediaTypeOrNull()

        val jsonBody = json.toString()
        val requestBody = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d(TAG, "onFailure: $e.message")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    val str = response.body?.string()
                    Log.d(TAG, "onResponse: $str")
                }
            }
        })
    }
}
