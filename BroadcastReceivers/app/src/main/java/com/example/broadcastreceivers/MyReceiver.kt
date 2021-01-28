package com.example.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("pulkit","broadcast received")
        Toast.makeText(context,"Charger connected",Toast.LENGTH_SHORT).show()
//        context.startActivity(Intent(context,MainActivity::class.java))
    }
}