package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class MyJobService:JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Toast.makeText(this,"scheduled job has been called!",Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}