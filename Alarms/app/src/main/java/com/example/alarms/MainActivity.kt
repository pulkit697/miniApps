package com.example.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(baseContext,MainActivity2::class.java)
        val pendingIntent = PendingIntent.getActivity(baseContext,123,intent,PendingIntent.FLAG_ONE_SHOT)

        val pendingIntent2 = PendingIntent.getActivity(baseContext,1234,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager



        btStart.setOnClickListener {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3000, pendingIntent)
        }

        btRepeat.setOnClickListener {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime() + 3000,3000,pendingIntent2)
        }

    }
}