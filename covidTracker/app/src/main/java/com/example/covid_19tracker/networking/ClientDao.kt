package com.example.covid_19tracker.networking

import com.example.covid_19tracker.data.StatewiseItem
import retrofit2.Response
import retrofit2.http.GET

interface ClientDao {
    @GET("data.json")
    suspend fun getResponseData():Response<com.example.covid_19tracker.data.Response>
}