package com.example.zomatoapi

data class LocationDetail(
    val link: String,
    val locality: Locality,
    val nearby_restaurants: List<NearbyRestaurant>,
    val popularity: Popularity
)

data class SearchResult(
    val restaurants: List<Restaurant>,
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int
)

data class Locality(
    val city_id: String,
    val city_name: String,
    val country_id: String,
    val country_name: String,
    val entity_id: String,
    val entity_type: String,
    val latitude: String,
    val longitude: String,
    val title: String
)

data class NearbyRestaurant(
    val all_reviews: List<AllReview>,
    val all_reviews_count: String,
    val average_cost_for_two: String,
    val cuisines: String,
    val currency: String,
    val deeplink: String,
    val events_url: String,
    val featured_image: String,
    val has_online_delivery: String,
    val has_table_booking: String,
    val id: String,
    val is_delivering_now: String,
    val location: Location,
    val menu_url: String,
    val name: String,
    val phone_numbers: String,
    val photo_count: String,
    val photos: List<Photo>,
    val photos_url: String,
    val price_range: String,
    val thumb: String,
    val url: String,
    val user_rating: UserRating
)

data class Popularity(
    val nightlife_index: String,
    val popularity: String,
    val top_cuisines: List<String>
)

data class AllReview(
    val comments_count: String,
    val id: String,
    val likes: String,
    val rating: String,
    val rating_color: String,
    val rating_text: String,
    val review_text: String,
    val review_time_friendly: String,
    val timestamp: String,
    val user: User
)

data class Location(
    val address: String,
    val city: String,
    val country_id: String,
    val latitude: String,
    val locality: String,
    val longitude: String,
    val zipcode: String
)

data class Photo(
    val caption: String,
    val comments_count: String,
    val friendly_time: String,
    val height: String,
    val id: String,
    val likes_count: String,
    val res_id: String,
    val thumb_url: String,
    val timestamp: String,
    val url: String,
    val user: UserX,
    val width: String
)

data class UserRating(
    val aggregate_rating: String,
    val rating_color: String,
    val rating_text: String,
    val votes: String
)

data class User(
    val foodie_color: String,
    val foodie_level: String,
    val foodie_level_num: String,
    val name: String,
    val profile_deeplink: String,
    val profile_image: String,
    val profile_url: String,
    val zomato_handle: String
)

data class UserX(
    val foodie_color: String,
    val foodie_level: String,
    val foodie_level_num: String,
    val name: String,
    val profile_deeplink: String,
    val profile_image: String,
    val profile_url: String,
    val zomato_handle: String
)

data class Restaurant(
    val restaurant: RestaurantX
)

data class RestaurantX(
    val Re: Re,
    val all_reviews: AllReviews,
    val all_reviews_count: Int,
    val apikey: String,
    val average_cost_for_two: Int,
    val book_again_url: String,
    val book_form_web_view_url: String,
    val cuisines: String,
    val currency: String,
    val deeplink: String,
    val establishment: List<String>,
    val establishment_types: List<Any>,
    val events_url: String,
    val featured_image: String,
    val has_online_delivery: Int,
    val has_table_booking: Int,
    val highlights: List<String>,
    val id: String,
    val include_bogo_offers: Boolean,
    val is_book_form_web_view: Int,
    val is_delivering_now: Int,
    val is_table_reservation_supported: Int,
    val is_zomato_book_res: Int,
    val location: Location,
    val medio_provider: Boolean,
    val menu_url: String,
    val mezzo_provider: String,
    val name: String,
    val offers: List<Any>,
    val opentable_support: Int,
    val phone_numbers: String,
    val photo_count: Int,
    val photos_url: String,
    val price_range: Int,
    val store_type: String,
    val switch_to_order_menu: Int,
    val thumb: String,
    val timings: String,
    val url: String,
    val user_rating: UserRating
)

data class Re(
    val has_menu_status: HasMenuStatus,
    val is_grocery_store: Boolean,
    val res_id: Int
)

data class AllReviews(
    val reviews: List<Review>
)


data class HasMenuStatus(
    val delivery: Int,
    val takeaway: Int
)

data class Review(
    val review: List<Any>
)

data class RatingObj(
    val bg_color: BgColor,
    val title: Title
)

data class BgColor(
    val tint: String,
    val type: String
)

data class Title(
    val text: String
)