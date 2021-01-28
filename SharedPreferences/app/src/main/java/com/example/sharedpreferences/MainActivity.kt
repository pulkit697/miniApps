package com.example.sharedpreferences

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getPreferences(Context.MODE_PRIVATE)
        val color = pref.getInt("COLOR",Color.WHITE)
        layoutParent.setBackgroundColor(color)

        fun saveColor(color: Int)
        {
            layoutParent.setBackgroundColor(color)
            val editor = pref.edit()
            editor.putInt("COLOR",color)
            editor.apply()
        }

        btRed.setOnClickListener { saveColor(Color.RED) }
        btGreen.setOnClickListener { saveColor(Color.GREEN) }
        btPurple.setOnClickListener { saveColor(if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            resources.getColor(R.color.purple_200,theme)
        else
            resources.getColor(R.color.purple_200)) }
    }

}