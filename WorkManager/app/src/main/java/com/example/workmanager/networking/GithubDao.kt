package com.example.workmanager.networking

import com.example.workmanager.model.ApiResult
import com.example.workmanager.model.GitHubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubDao {

    @GET("search/users")
    suspend fun getUsers(@Query("q")query:String):Response<ApiResult>
}