package com.example.zomatoapi.data

data class Popularity(
    val city: String,
    val nearby_res: List<String>,
    val nightlife_index: String,
    val nightlife_res: String,
    val popularity: String,
    val popularity_res: String,
    val subzone: String,
    val subzone_id: Int,
    val top_cuisines: List<String>
)