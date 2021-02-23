package com.example.sensor

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager:SensorManager? = getSystemService<SensorManager>()
        if(sensorManager==null) {
            Toast.makeText(this,"sensors not found",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            val list  = sensorManager.getSensorList(Sensor.TYPE_ALL)
            list.forEach{
                Log.d("pulkit","${it.name}    ${it.vendor} " )
            }
        }


    }
}