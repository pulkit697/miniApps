package com.example.itunes.data.api

import com.example.itunes.data.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    suspend fun getTracks(@Query("term")query:String):Response<SearchResult>

}
