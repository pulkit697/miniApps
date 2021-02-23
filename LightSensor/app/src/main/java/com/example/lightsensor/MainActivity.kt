package com.example.lightsensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var eventListener: SensorEventListener
    lateinit var eventListener2: SensorEventListener
    var lightSensor: Sensor?=null
    var tempSensor: Sensor?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService()!!
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        eventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                // do here
                val s = "Light: ${event!!.values[0]}"
                tvText.text = s
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // DO NOTHING
            }
        }
        eventListener2 = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                // do here
                val s = "Temperature: ${event!!.values[0]}"
                tvText2.text = s
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // DO NOTHING
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(lightSensor!=null) {
            sensorManager.registerListener(eventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if(tempSensor!=null) {
            sensorManager.registerListener(eventListener2, tempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if(lightSensor!=null) {
            sensorManager.unregisterListener(eventListener)
        }
        if(tempSensor!=null) {
            sensorManager.unregisterListener(eventListener2)
        }
    }
}