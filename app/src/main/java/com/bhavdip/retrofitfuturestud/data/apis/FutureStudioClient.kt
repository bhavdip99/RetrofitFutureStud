package com.bhavdip.retrofitfuturestud.data.apis

import com.bhavdip.retrofitfuturestud.data.model.Tutorial
import com.bhavdip.retrofitfuturestud.data.model.FutureStudioRssFeed
import com.bhavdip.retrofitfuturestud.data.model.UserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.sql.Date


interface FutureStudioClient {
    @get:GET("/user/info")
    val userInfo: Call<UserInfo?>?

    @PUT("/user/info")
    fun updateUserInfo(
        @Body userInfo: UserInfo?
    ): Call<UserInfo?>?

    @DELETE("/user")
    fun deleteUser(): Call<Void?>?

    // example for passing a full URL
    @GET("https://futurestud.io/tutorials/rss/")
    fun getRssFeed(): Call<FutureStudioRssFeed?>?

    //Used @Body as parameter
    @PUT("/user/info")
    fun updateUserInfo1(
        @Body userInfo: UserInfo?
    ): Call<Void?>?

    //only @Url used as parameter
    @GET
    fun getUserProfilePhoto(
        @Url profilePhotoUrl: String?
    ): Call<ResponseBody?>?

    // passing "null" will ignore query
    @GET("/tutorials")
    fun getTutorials(
        @Query("page") page: Int?,
        @Query("order") order: String?,
        @Query("author") author: String?,
        @Query("published_at") date: Date?
    ): Call<List<Tutorial?>?>?
}
