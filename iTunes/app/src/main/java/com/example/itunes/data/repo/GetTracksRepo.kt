package com.example.itunes.data.repo

import com.example.itunes.data.api.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTracksRepo {

    suspend fun searchTracks(query:String) = Client.api.getTracks(query)
}