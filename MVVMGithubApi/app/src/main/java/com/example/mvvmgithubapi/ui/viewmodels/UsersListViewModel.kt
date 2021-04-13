package com.example.mvvmgithubapi.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmgithubapi.data.model.GithubUser
import com.example.mvvmgithubapi.data.repository.GithubApiRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersListViewModel: ViewModel() {
    val users = MutableLiveData<List<GithubUser>?>()
    fun fetchUsers()
    {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){GithubApiRepo.getAllUsers()}
            if (response.isSuccessful){
                response.body()?.let {
                    users.postValue(it)
                }
            }
        }

        /*
        runIO(){
            runSomething()
        }
        */
    }

    fun fetchSearchedUsers(name:String) = liveData(Dispatchers.IO) {
        Log.d("pulkit","searched response")
        val response = withContext(Dispatchers.IO){GithubApiRepo.getSearchResult(name)}
        Log.d("pulkit","got response")
        if(response.isSuccessful){
            Log.d("pulkit","response successful")
            response.body()?.let {
                Log.d("pulkit", "response transmitting...")
                emit(it.items)
                Log.d("pulkit", "response transmitted")
            }
        }
    }
    /* There is something extensions functions in kotlin
    You can replace viewModelScope.launch above with smaller syntax using this
     */
    /*
    private fun ViewModel.runIO(function: () -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO) {  }
    }
     */
    /*
    Now this can run functions which are to be run on coroutines scope
    private fun ViewModel.runIO(function: CoroutineScope.() -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO) {  }
    }

     */
}