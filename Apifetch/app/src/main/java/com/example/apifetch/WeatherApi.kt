package com.example.apifetch

import com.example.apifetch.weatherdata.WeatherData
import retrofit2.Call
import retrofit2.http.GET

const val api_key = "5966094c1fe70180dcb1423deb995d31"

interface WeatherApi {


    @GET("/v1/weather/q=london&appid=5966094c1fe70180dcb1423deb995d31")
    fun getData():Call<WeatherData>

}