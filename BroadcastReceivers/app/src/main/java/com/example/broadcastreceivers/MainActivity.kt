package com.example.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val psr=powerStateReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(psr,intentFilter)
    }
    inner class powerStateReceiver:BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context,"power changed",Toast.LENGTH_SHORT).show()
            startActivity(Intent(context,MainActivity::class.java))
        }
    }
}