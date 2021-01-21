package com.example.apifetch

import com.example.apifetch.weatherdata.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "5966094c1fe70180dcb1423deb995d31"

interface WeatherApi {
    @GET("weather?q=london&appid=${api_key}")
    fun getData():Call<WeatherData>

    @GET("weather")
    fun getDataBySearch(@Query("q")country:String,@Query("appid")api:String = api_key):Call<WeatherData>

}