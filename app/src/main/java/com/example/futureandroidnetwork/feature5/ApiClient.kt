package com.example.futureandroidnetwork.feature5

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://reqres.in/api/"
    val getClient: IApiService
        get() {
            val gsonBuilder = GsonBuilder().setLenient().create()
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build()

            return retrofit.create(IApiService::class.java)
    }
}