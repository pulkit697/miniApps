package com.example.workmanager.ui

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager.networking.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubWorker(appContext: Context, params: WorkerParameters):CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val response = withContext(Dispatchers.IO){Client.api.getUsers("pulkit")}
        return if(response.isSuccessful) {
            Log.d("pulkit","worker success")
            Result.success()
        }else{
            Log.d("pulkit","worker failure")
            Result.failure()
        }
    }
}