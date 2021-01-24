package com.example.futureandroidnetwork.codelab2

import com.example.futureandroidnetwork.codelab2.model.GithubRepo
import com.example.futureandroidnetwork.codelab2.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IGithubService {
    @GET("users")
    fun getUsersList(): Call<List<GithubUser>>


    @GET("users/{user}/repos")
    fun getReposList(
        @Path("user") user: String
    ): Call<List<GithubRepo>>
}