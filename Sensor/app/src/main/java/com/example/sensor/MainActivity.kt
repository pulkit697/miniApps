package com.example.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var sensorEventListener: SensorEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService()!!
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("Pulkit","""
                    Sensor data changed to: ${event!!.values[0]}
                """.trimIndent())
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                /* NOTHING HERE*/
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorEventListener,proximitySensor,10000*1000)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }

}