package com.example.mvvmgithubapi.data.model

data class SearchResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<GithubUser>? = null
)