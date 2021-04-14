package com.example.itunes.data.repo

import android.app.Application
import com.example.itunes.data.db.TracksDatabase
import com.example.itunes.data.model.SingleTrack

class TrackFromDBRepo(application: Application) {

    private val database = TracksDatabase.getInstance(application)

    fun insertTrackIntoDB(track:SingleTrack) = database.getRoomDao().insert(track)

    fun fetchTracksFromDB() = database.getRoomDao().getAllSavedTracks()

    fun fetchSearchedTracksFromDB(query:String) = database.getRoomDao().searchSavedTracks(query)



}