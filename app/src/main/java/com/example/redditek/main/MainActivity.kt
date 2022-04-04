package com.example.redditek.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.redditek.R
import com.example.redditek.oauth.Auth



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
    }

    fun startSignIn(view: View?) {
        Log.e(TAG,"JE PASSE ICI")
        val url = Auth.getAuthUrl()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Auth.initAccessToken(intent)
        Log.e(TAG, "///////////////////////////////////////////onResume/////////////////////////////////////////////////////////////////////////")
        Auth.getAccessToken()?.let { Log.e(TAG, it) }
        Auth.getRefreshToken()?.let { Log.e(TAG, it) }
    }
}
