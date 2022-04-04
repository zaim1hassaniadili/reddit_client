package com.example.redditek.services

import android.content.Context
import android.util.Log
import com.example.redditek.MyPreference
import com.example.redditek.oauth.Auth
import com.example.redditek.oauth.SomeCallBack
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CountDownLatch


class RedditService(context: Context) {
    var TAG = "Tokken: "
    private val client = OkHttpClient()
    var BASE_URL = "https://oauth.reddit.com/"
    val myPreference = MyPreference(context)
    var access_token = "Bearer " +  myPreference.access_token

    fun getUserData(): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var  data: String? = null;

        Log.d("token", access_token)

        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL + "api/v1/me")
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)
    }

    fun search(str : String): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var  data: String? = null;

        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"subreddits/search?q="+str)
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()

                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)

    }
    fun getFilterFeed(str: String="", filter: String): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var  data: String? = null;
        var url =BASE_URL +"best/"

        if(str == ""){
          url =BASE_URL +"/${filter}"
        }else{
          url =BASE_URL + "r/${str}/${filter}"
        }
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(url)
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()

                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)

    }



    fun getSubRedditAbout(str : String): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1 $access_token  $str")
        var  data: String? = null;

        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +str.toLowerCase() +"/about.json")
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()

                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)

    }

    fun getUserComment(str : String): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var  data: String? = null;

        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"user/"+str)
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()

                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)

    }

    fun getDefaultFeed(str : String): JSONObject{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var  data: String? = null;

        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"r/"+str)
            .build()

        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()

                countDownLatch.countDown();
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val json : String = response.body?.string() as String
                    //val data: JSONObject?
                    try {
                        data = json
                        Log.d("Okhttp","Here we are ${json}")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        Log.d("Okhttp OUT", "Here the data $data")

        return JSONObject(data)
    }
    //subscribe automatically use second argument "false" to unsubscribe
    fun subscribe(subreddit : String, isSub: Boolean = true): Boolean{
        Log.d("Okhttp","OKhttp  ===> Log 1")
        var sub = if(isSub) "sub" else "unsub";
        var str ="text/plain"
        var mediaType = str.toMediaType()
        var b = ""
        var body = b.toRequestBody(mediaType)
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"/api/subscribe?action=$sub&sr_name=$subreddit")
            .method("POST", body)
            .build()
        var countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)

                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return true

    }

    fun showStatus(setter : Boolean): Boolean {
        Log.d("Show status","Show status  ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("show_presence", setter)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {

                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return setter
    }

    fun allowLocation(setter : Boolean): Boolean {
        Log.d("Show status","Show status  ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("show_location_based_recommendations", setter)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        myPreference.allow_location = setter
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return setter
    }

    fun changeEighteen(setter : Boolean): Boolean {
        Log.d("Show status","Show status  ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("over_18", setter)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return setter
    }

    fun hideRobots(setter : Boolean): Boolean {
        Log.d("Email","Email  ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("hide_from_robots", setter)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return setter
    }


    fun allowSubscriber(setter : Boolean): Boolean {
        Log.d("enable follower","Enable follower  ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("enable_followers", setter)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        myPreference.enable_follower = setter
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return setter
    }

    fun acceptPrivateMessage(who : String): String {
        Log.d("Accept Private Messages","Accept Private Message ===> Log 1")
        var  data: String? = null;
        val obj = JSONObject()
        obj.put("accept_pms", who)
        val str ="application/json"
        val mediaType = str.toMediaType()
        val request = Request.Builder()
            .header("Authorization",access_token)
            .url(BASE_URL +"api/v1/me/prefs")
            .patch(obj.toString().toRequestBody(mediaType))
            .build()

        val countDownLatch= CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Okhttp","OKhttp  ===> Log 2")
                Log.e("Okhttp","Oups something went wrong"+ e.message)
                e.printStackTrace()
                countDownLatch.countDown();
            }
            override fun onResponse(call: Call, response: Response) {
                Log.d("Okhttp","ICICICICIICICIICIICICIC")
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val json : String = response.body?.string() as String
                    try {
                        myPreference.accept_private_messages = who
                        Log.d("Okhttp","Here we are $json")
                    } catch ( e : Exception) {
                        e.printStackTrace()
                    }
                }
                Log.d("Okhttp","OKhttp  ===> Log 2")
                countDownLatch.countDown();
            }
        })
        countDownLatch.await();
        Log.d("Okhttp","OKhttp  ===> Log 3")
        return who
    }

}
