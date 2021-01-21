package com.example.futureandroidnetwork.feature5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiService {
    @GET("users/{id}")
    fun getUserDetail(@Path("id") id: Int): Call<ApiResponse>
}