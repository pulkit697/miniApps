package com.example.itunes.data.model

data class SearchResult(
	val resultCount: Int,
	val results: List<SingleTrack>? = null
)
