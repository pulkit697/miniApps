package com.example.zomatoapi


import com.example.zomatoapi.data.GeoCode
import com.example.zomatoapi.data.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ZomatoApi {

    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
    @GET("geocode")
    fun getRestaurantsByLocation(@Query("lat")lat:Double, @Query("lon")lon: Double):Call<GeoCode>

    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
    @GET("search")
    fun getRestaurantsBySearch(@Query("q")query:String): Call<SearchResult>
}