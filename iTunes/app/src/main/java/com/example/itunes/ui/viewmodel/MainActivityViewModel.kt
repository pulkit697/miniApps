package com.example.itunes.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.itunes.data.db.TracksDatabase
import com.example.itunes.data.model.SearchResult
import com.example.itunes.data.repo.GetTracksRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel() {

    fun getAllTracks(query:String) = liveData(Dispatchers.IO) {
        val response = withContext(Dispatchers.IO) {GetTracksRepo().searchTracks(query)}
        if(response.isSuccessful)
        {
            Log.d("pulkit",response.body().toString())
            response.body()?.let {
            Log.d("pulkit",""+it.resultCount)
                emit(it)
            }
        }
    }
}