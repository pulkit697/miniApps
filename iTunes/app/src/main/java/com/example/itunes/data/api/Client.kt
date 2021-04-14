package com.example.itunes.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Client {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: SearchService = retrofit.create(SearchService::class.java)
}