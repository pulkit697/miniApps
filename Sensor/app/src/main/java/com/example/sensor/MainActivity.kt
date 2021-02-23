package com.example.sensor

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var sensorEventListener: SensorEventListener
    val colors = arrayOf(Color.RED,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.YELLOW)

    //9fae02aa72efded5152edccff26374d7da6ec012

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
                flBottom.setBackgroundColor(colors[Random.nextInt(6)])
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                /* NOTHING HERE*/
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorEventListener,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }

}