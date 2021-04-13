package com.example.mvvmgithubapi.data.repository

import com.example.mvvmgithubapi.data.api.Client

object GithubApiRepo {

    suspend fun getAllUsers() = Client.api.getUsersList()

    suspend fun getSearchResult(name:String) = Client.api.getSearchUsers(name)
}