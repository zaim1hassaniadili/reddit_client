package com.example.redditek

import android.content.Context

class MyPreference(context : Context) {
    private val prefs = context.getSharedPreferences("com.example.redditek", Context.MODE_PRIVATE)

    var isFirstTime: Boolean
        get() = prefs.getBoolean("isFirstTime", true)
        set(value) = prefs.edit().putBoolean("isFirstTime", value).apply()

    var isLoggedIn: Boolean
        get() = prefs.getBoolean("isLoggedIn", false)
        set(value) = prefs.edit().putBoolean("isLoggedIn", value).apply()

    var isLoggedOut: Boolean
        get() = prefs.getBoolean("isLoggedOut", false)
        set(value) = prefs.edit().putBoolean("isLoggedOut", value).apply()

    var access_token: String?
        get() = prefs.getString("access_token", "")
        set(value) = prefs.edit().putString("access_token", value).apply()

    var accept_private_messages : String?
        get() = prefs.getString("accept_private_messages", "")
        set(value) = prefs.edit().putString("accept_private_messages", value).apply()

    var enable_follower : Boolean
        get() = prefs.getBoolean("enable_follower", false)
        set(value) = prefs.edit().putBoolean("enable_follower", value).apply()

    var allow_location : Boolean
        get() = prefs.getBoolean("allow_location", false)
        set(value) = prefs.edit().putBoolean("allow_location", value).apply()
        
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}