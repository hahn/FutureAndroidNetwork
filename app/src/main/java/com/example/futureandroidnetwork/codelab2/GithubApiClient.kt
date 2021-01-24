package com.example.futureandroidnetwork.codelab2

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiClient {

    private const val BASE_URL = "https://api.github.com/"
    val getClient: IGithubService
        get() {
            val gsonBuilder = GsonBuilder().setLenient().create()
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .build()

            return retrofit.create(IGithubService::class.java)
        }
}