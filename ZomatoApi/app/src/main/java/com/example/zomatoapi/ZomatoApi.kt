package com.example.zomatoapi


import com.example.zomatoapi.data.SearchResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ZomatoApi {

    @GET("search")
    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
    suspend fun getData(): Call<SearchResult>
//
//    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
//    @GET("geocode")
//    suspend fun getRestaurantsByLocation(@Query("lat")lat:Double, @Query("lon")lon: Double):Response<LocationDetail>
//
//    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
//    @GET("search?entity_id={id}&q={str}")
//    suspend fun getRestaurantBySearch(@Path("id")id:Long,@Path("str")str:String):Response<SearchResult>

}