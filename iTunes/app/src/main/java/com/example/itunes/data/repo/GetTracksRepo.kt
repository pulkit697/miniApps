package com.example.itunes.data.repo

import com.example.itunes.data.api.Client

class GetTracksRepo {

    suspend fun searchTracks(query:String) = Client.api.getTracks(query)
}