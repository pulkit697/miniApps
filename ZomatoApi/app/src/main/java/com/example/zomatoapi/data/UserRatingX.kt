package com.example.zomatoapi.data

data class UserRatingX(
    val aggregate_rating: String,
    val rating_color: String,
    val rating_obj: RatingObjX,
    val rating_text: String,
    val votes: Int
)