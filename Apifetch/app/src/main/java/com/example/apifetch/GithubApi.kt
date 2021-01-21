package com.example.apifetch

import com.example.apifetch.githubData.UserList
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {

    @GET("/users")
    fun getUsers():Call<UserList>

}