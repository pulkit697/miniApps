package com.example.itunes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SingleTrack(
	val trackName: String? = null,
	val currency: String? = null,
	val trackPrice: Double? = null,
	val artworkUrl60: String? = null,
	val artistName: String? = null,
	@PrimaryKey
	val trackId:Long?=null
)
