package com.example.zomatoapi.data

data class UserRating(
    val aggregate_rating: Any,
    val rating_color: String,
    val rating_obj: RatingObj,
    val rating_text: String,
    val votes: Int
)