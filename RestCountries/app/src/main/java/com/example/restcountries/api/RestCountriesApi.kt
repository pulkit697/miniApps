package com.example.restcountries.api

import com.example.restcountries.data.RegionResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {

    @GET("region/{reg}")
    fun getCountries(@Path("reg") region:String):Call<RegionResult>

}