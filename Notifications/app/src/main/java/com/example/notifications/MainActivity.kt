package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

const val CHANNEL_1 = "Channel_1"
const val CHANNEL_2 = "Channel_2"
const val REQUEST_CODE = 123

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_1, CHANNEL_1, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            val notification1 = NotificationCompat.Builder(this, CHANNEL_1)
                    .setContentTitle("Notification")
                    .setContentText("silent notification")
                    .setSmallIcon(R.drawable.ic_android)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build()
            bt1.setOnClickListener { notificationManager.notify(1, notification1) }

            // clickable
            val intent = Intent()
            intent.apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://www.google.com")
            }
            val pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification2 = NotificationCompat.Builder(this, CHANNEL_1)
                    .setContentTitle("Notification")
                    .setContentText("clickable notification")
                    .setSmallIcon(R.drawable.ic_android)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true) //remove notification after click
                    .build()
            bt2.setOnClickListener { notificationManager.notify(2, notification2) }

            //push
            val channel2 = NotificationChannel(CHANNEL_2, CHANNEL_2, NotificationManager.IMPORTANCE_HIGH)
            channel2.apply {
                enableLights(true)
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel2)
        }
            val builder = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                                Notification.Builder(this, CHANNEL_2)
                        else
                            Notification.Builder(this)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setPriority(Notification.PRIORITY_MAX)

        val notification3 = builder
                    .setContentText("push notification")
                    .setContentTitle("Notification")
                    .setSmallIcon(R.drawable.ic_android)
                    .build()
            bt3.setOnClickListener { notificationManager.notify(3,notification3) }

    }
}