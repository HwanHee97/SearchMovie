package com.example.searchmovie.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitClient: Retrofit?=null

    fun getClient(baseUrl: String):Retrofit?{
        val client= OkHttpClient.Builder()
        client.retryOnConnectionFailure(true)
        if (retrofitClient == null){
            retrofitClient=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}