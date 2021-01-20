package com.example.zomatoapi


import retrofit2.Response
import retrofit2.http.*


interface ZomatoApi {

    @Headers("accept:application-json","user-key: 1b3c8b37ea96785391fa55c288ac385c")
    @GET("search")
    suspend fun getData():Response<LocationDetail>
//
//    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
//    @GET("geocode")
//    suspend fun getRestaurantsByLocation(@Query("lat")lat:Double, @Query("lon")lon: Double):Response<LocationDetail>
//
//    @Headers("user-key: 1b3c8b37ea96785391fa55c288ac385c")
//    @GET("search?entity_id={id}&q={str}")
//    suspend fun getRestaurantBySearch(@Path("id")id:Long,@Path("str")str:String):Response<SearchResult>

}