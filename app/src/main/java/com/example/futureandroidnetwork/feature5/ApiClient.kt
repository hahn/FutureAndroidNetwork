package com.example.futureandroidnetwork.feature5

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object ApiClient {
    private const val BASE_URL = "https://reqres.in/api/"
    val getClient: IApiService
        get() {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            return retrofit.create(IApiService::class.java)
    }
}