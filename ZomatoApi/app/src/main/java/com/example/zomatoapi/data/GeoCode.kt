package com.example.zomatoapi.data

data class GeoCode(
    val link: String,
    val location: LocationX,
    val nearby_restaurants: List<NearbyRestaurant>,
    val popularity: Popularity
)