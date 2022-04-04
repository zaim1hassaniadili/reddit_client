package com.example.redditek.services

import android.app.Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
  var BASE_URL = "https://oauth.reddit.com/"

  fun create() : ServiceApi{

    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()
    return retrofit.create(ServiceApi::class.java)

  }

}
