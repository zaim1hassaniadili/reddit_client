package com.example.redditek.ui.home

import com.example.redditek.services.RedditService
import android.content.Context
import android.util.Log
import org.json.*


class SubRedditServices(context: Context){

    var redditService = RedditService(context)

    fun getHomeMaterial(str: String, filter: String):MutableMap<String,Array<String>>{
        var titleArray = arrayOf("","","","","");
        var descriptionArray = arrayOf("","","","","");
        var subNameArray = arrayOf("","","","","");

        var obj: JSONObject? = null
        var rtn = mutableMapOf<String, Array<String>>()

        if(str ==""){
            obj = redditService.getFilterFeed(str, filter);
        }else{
            obj = redditService.getDefaultFeed(str);
        }
        var children = obj.getJSONObject("data").getJSONArray("children");

        for( i in 0..4){
            var temp1 = children.getJSONObject(i).getJSONObject("data").get("title")
            titleArray.set(i,temp1.toString())
            rtn.set("title",titleArray)

            var temp2 = children.getJSONObject(i).getJSONObject("data").get("selftext")
            descriptionArray.set(i,temp2.toString())
            rtn.set("description",descriptionArray)

            var temp3 = children.getJSONObject(i).getJSONObject("data").get("subreddit_name_prefixed")
            subNameArray.set(i, temp3.toString())
            rtn.set("subname", subNameArray)

            var temp4 =  getSubRedditDescription(children)
            rtn.set("subredditDescriptiion",temp4)

            var temp5 =  getSubRedditSubscriber(children)
            rtn.set("subredditSubscriber",temp5)

        }



        return rtn
    }
    fun getSubRedditDescription(children: JSONArray): Array<String>{
        var SubDescriptionArray = arrayOf("","","","","");

        for( i in 0..4){
            var title = children.getJSONObject(i).getJSONObject("data").get("subreddit_name_prefixed")
            var newObj = redditService.getSubRedditAbout(title.toString());

            SubDescriptionArray.set(i, newObj.getJSONObject("data").get("public_description").toString())
        }
        return SubDescriptionArray;

    }

    fun getSubRedditSubscriber(children :JSONArray): Array<String>{
        var SubSubscriberArray = arrayOf("","","","","");
        for( i in 0..4){
            var title = children.getJSONObject(i).getJSONObject("data").get("subreddit_name_prefixed")
            var str = title.toString()

            var newObj = redditService.getSubRedditAbout(str);
            SubSubscriberArray.set(i, newObj.getJSONObject("data").get("subscribers").toString())
        }
        return SubSubscriberArray;

    }
    fun getSubscribe(str: String, isSub : Boolean = true){
        redditService.subscribe(str,isSub)
    }

}