package com.example.itunes.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.itunes.data.model.SingleTrack

interface SavingTracksDao {
    @Insert
    fun insert(track:SingleTrack)

    @Delete
    fun deletePerviousData()

    @Query("SELECT * FROM SingleTrack")
    fun getAllSavedTracks():List<SingleTrack>

    @Query("SELECT * FROM SingleTrack WHERE trackName LIKE :query OR artistName like :query")
    fun searchSavedTracks(query:String):List<SingleTrack>
}