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
import kotlin.math.roundToInt
import kotlin.random.Random



class MainActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    lateinit var accSensor:Sensor
    lateinit var sensorEventListener: SensorEventListener
    val colors = arrayOf(Color.RED,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.YELLOW)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService()!!
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("Pulkit","""
                    -----
                    ax = ${event!!.values[0]}
                    ay = ${event.values[1]}
                    az = ${event.values[2]}
                    -----
                """.trimIndent())
//                flBottom.setBackgroundColor(colors[Random.nextInt(6)])
                flBottom.setBackgroundColor(Color.rgb(
                    getColorAcc(event.values[0]),
                    getColorAcc(event.values[1]),
                    getColorAcc(event.values[2])
                ))
                flTop.setBackgroundColor(Color.rgb(
                    getColorAcc(event.values[1]),
                    getColorAcc(event.values[2]),
                    getColorAcc(event.values[0])
                ))
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                /* NOTHING HERE*/
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorEventListener,accSensor,1000*1000*100)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }

    fun getColorAcc(f:Float):Int = (((f+12)/24) *255).roundToInt()

}