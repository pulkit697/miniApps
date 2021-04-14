package com.example.itunes.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.itunes.data.model.SingleTrack
import com.example.itunes.data.repo.GetTracksRepo
import com.example.itunes.data.repo.TrackFromDBRepo
import com.example.itunes.utils.ROOM_DB_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val databaseRepo = TrackFromDBRepo(getApplication<Application>())

    fun getTracksFromDB() =
            liveData{
                val fetchedList = withContext(Dispatchers.IO){databaseRepo.fetchTracksFromDB()}
                emit(fetchedList)
            }

    fun getSearchedTracksFromDB(query: String) =
        liveData{
            val fetchedList = withContext(Dispatchers.IO){databaseRepo.fetchSearchedTracksFromDB(query)}
            emit(fetchedList)
        }

    fun getAllTracks(query:String) =
        liveData(Dispatchers.IO) {
            val response = withContext(Dispatchers.IO) {GetTracksRepo().searchTracks(query)}
            if(response.isSuccessful) {
                Log.d(ROOM_DB_TAG,response.body().toString())
                response.body()?.let {
                    Log.d(ROOM_DB_TAG,""+it.resultCount)
                    emit(it)
                }
            }
        }

    fun insertFetchedTracksIntoDB(tracksList:List<SingleTrack>)
    {
        viewModelScope.launch(Dispatchers.IO) {
            for (track in tracksList){
                databaseRepo.insertTrackIntoDB(track)
            }
        }
    }
}