package com.example.zomatoapi

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://developers.zomato.com/api/v2.1")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(ZomatoApi::class.java)

}