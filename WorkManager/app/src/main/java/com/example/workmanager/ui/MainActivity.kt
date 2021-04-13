package com.example.workmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import com.example.workmanager.R
import com.example.workmanager.model.GitHubUser
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun scheduleWork(view: View) {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build()
//        val workRequest = OneTimeWorkRequestBuilder<GitHubWorker>()
//                .setInitialDelay(30,TimeUnit.SECONDS)
//                .setConstraints(constraints)
//                .build()
        val workRequest = PeriodicWorkRequestBuilder<GitHubWorker>(1,TimeUnit.DAYS)
                .setInitialDelay(8,TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }
}