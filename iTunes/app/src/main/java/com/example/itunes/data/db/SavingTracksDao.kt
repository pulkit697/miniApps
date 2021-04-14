package com.example.itunes.data.db

import androidx.room.*
import com.example.itunes.data.model.SingleTrack

@Dao
interface SavingTracksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track:SingleTrack)

    @Query("SELECT * FROM SingleTrack")
    fun getAllSavedTracks():List<SingleTrack>

    @Query("SELECT * FROM SingleTrack WHERE trackName LIKE :query OR artistName like :query")
    fun searchSavedTracks(query:String):List<SingleTrack>
}