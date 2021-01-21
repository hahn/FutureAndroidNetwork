package com.example.futureandroidnetwork.feature5

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiService {
    @GET("users/{id}")
    fun getUserDetail(@Path("id") id: Int): Call<ResponseBody>
}