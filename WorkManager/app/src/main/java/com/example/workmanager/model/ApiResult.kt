package com.example.workmanager.model

data class ApiResult(val total_count:Int,val incomplete_results:Boolean,val items:ArrayList<GitHubUser>)
