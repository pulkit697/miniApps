package com.example.zomatoapi.data

data class SearchResult(
    val restaurants: List<Restaurant>,
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int
)