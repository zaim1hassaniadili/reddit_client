package com.example.redditek

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.redditek.oauth.Auth
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AuthenticationActivity  : AppCompatActivity() {

    private var access_token: String? = null
    private var refresh_token: String? = null

    private val CLIENT_ID = "Kkvyu6qZCle3DUWuEFSQmQ"

    private val REDIRECT_URI = "redditek://com.example.redditek.main/"

    private val STATE = "MY_RANDOM_STRING_1"

    private val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }

    fun startSignIn(view: View?) {
        val url = Auth.getAuthUrl()
        Log.e(TAG, url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        catchToken(intent)
    }

    fun catchToken(intent: Intent): Boolean {
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

                        callApiAuth(code)
                        return true
                    }
                }
            }
        }
        return false
    }
    private fun callApiAuth(code: String) : Boolean {
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
                data = JSONObject(json)
                access_token = data.optString("access_token")
                refresh_token = data.optString("refresh_token")
                if (access_token != null) {
                    nextActivity(access_token.toString())
                } else {
                    println("No access token")
                }
        }
    })
        return true
    }
    
    private fun nextActivity(accessToken: String) {
        println("token: $accessToken")
        val i = Intent(this, MainActivity::class.java)
        val myPreference = MyPreference(this)
        myPreference.access_token = accessToken
        myPreference.isLoggedIn = true
        startActivity(i)
       // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}