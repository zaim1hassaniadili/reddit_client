package com.example.redditek.oauth

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Base64
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CountDownLatch

object Auth{

    var access_token: String? = null
    private var refresh_token: String? = null
    private const val AUTH_URL = "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
            "&response_type=code&state=%s&redirect_uri=%s&" +
            "duration=permanent&scope=*"
    private const val CLIENT_ID = "Kkvyu6qZCle3DUWuEFSQmQ"
    private const val REDIRECT_URI = "redditek://com.example.redditek.main/"
    private const val STATE = "MY_RANDOM_STRING_1"
    private const val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"




    fun getAuthUrl(): String {
        return String.format(AUTH_URL, CLIENT_ID, STATE, REDIRECT_URI)
    }

    fun getAccessToken(): String? {
        return access_token
    }

    fun someFunction(someCallBack: SomeCallBack){
        var latch= CountDownLatch(1)
        if(access_token != null){
            latch.countDown()
            latch.await()
            someCallBack.onSuccess(access_token!!)
        }
        else{
            someCallBack.onFailure("Nope Still work to be done")
        }
    }

    fun getRefreshToken(): String? {
        return refresh_token
    }


    fun initAccessToken(intent: Intent) {

        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data

            if (uri!!.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e(TAG, "An error has occurred : $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == STATE) {
                    val code = uri.getQueryParameter("code")
                    if (code != null) {
                        Log.e(TAG, code)
                        callApiAuth(code, )
                    }
                }
            }
        }
    }

    private fun callApiAuth(code: String,) {
        OkHttpClient()
        val authString = "$CLIENT_ID:"
        val encodedAuthString: String = Base64.encodeToString(
            authString.toByteArray(),
            Base64.NO_WRAP
        )

        val request: Request = Request.Builder()
            .addHeader("User-Agent", "Sample App")
            .addHeader("Authorization", "Basic $encodedAuthString")
            .url(ACCESS_TOKEN_URL)
            .post(
                ("grant_type=authorization_code&code=" + code +
                        "&redirect_uri=" + REDIRECT_URI
                        ).toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
            )
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "ERROR: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val json : String = response.body?.string() as String
                val data: JSONObject?
                try {
                    data = JSONObject(json)
                    access_token = data.optString("access_token")
                    refresh_token = data.optString("refresh_token")

                    Log.e(TAG, "Access Token = $access_token")
                    Log.e(TAG, "Refresh Token = $refresh_token")


                } catch ( e : Exception) {
                    Log.e("Aimé", e.localizedMessage)
                    e.printStackTrace()
                }

            }

        })

    }

    fun refreshAccessToken() {
        val authString = "$CLIENT_ID:"
        val encodedAuthString: String = Base64.encodeToString(
            authString.toByteArray(),
            Base64.NO_WRAP
        )
        val request: Request = Request.Builder()
            .addHeader("User-Agent", "Sample App")
            .addHeader("Authorization", "Basic $encodedAuthString")
            .url(ACCESS_TOKEN_URL)
            .post(
                ("grant_type=refresh_token&refresh_token=" + refresh_token +
                        "&redirect_uri=" + REDIRECT_URI
                        ).toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
            )
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "ERROR: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val json : String = response.body?.string() as String
                val data: JSONObject?
                try {
                    data = JSONObject(json)
                    access_token = data.optString("access_token")
                    refresh_token = data.optString("refresh_token")
                    Log.e(TAG, "Access Token = $access_token")
                    Log.e(TAG, "Refresh Token = $refresh_token")

                } catch ( e : Exception) {
                    e.printStackTrace();

                }
            }
        })
    }


}
