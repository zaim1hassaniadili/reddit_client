package com.example.redditek.services


import com.example.redditek.services.Post.Post
import com.example.redditek.services.Propositions.Propositions
import retrofit2.Response
import retrofit2.http.*


interface ServiceApi {
    @GET("/{filter}")
    suspend fun getFilteredMainFeed(@Header("authorization") auth : String ,@Path("filter") filter: String =""): Response<Post>

    @GET("/r/{subreddit}/{filter}")
    suspend fun getFilteredSubFeed(@Header("authorization") auth : String, @Path("subreddit") subreddit : String , @Path("filter") filter: String =""): Response<Post>

    @GET("/api/subreddit_autocomplete_v2")
    suspend fun getPropositions(@Header("authorization") auth : String ,@Query("query") query : String): Response<Propositions>

    @POST("/api/subscribe")
    suspend fun subscribe(@Header("authorization") auth : String, @Query("action") action: String, @Query("sr_name") subreddit: String)

 /*   @GET("/r/{subreddit}/about.json")
    suspend fun getSubRedditAbout(@Header("authorization") auth : String):Response<About>*/
}
