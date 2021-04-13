package com.example.mvvmgithubapi.data.api

import com.example.mvvmgithubapi.data.model.GithubUser
import com.example.mvvmgithubapi.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUsersService {

    @GET("users")
    suspend fun getUsersList():Response<List<GithubUser>?>

    @GET("search/users")
    suspend fun getSearchUsers(@Query("q")name:String):Response<SearchResponse>

}
