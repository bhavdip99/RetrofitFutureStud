package com.bhavdip.retrofitfuturestud.data.apis

import com.bhavdip.retrofitfuturestud.data.model.GitHubUserRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubClient {

    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<List<GitHubUserRepo>>
}