package com.example.apifetch

import com.example.apifetch.data.CatFact
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CatApi {
    @GET("/facts/random")
    fun getCatFact(): Call<CatFact>
}