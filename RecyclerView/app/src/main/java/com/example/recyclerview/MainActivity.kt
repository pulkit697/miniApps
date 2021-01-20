package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.Fruit.Companion.generateFruitsRandom
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFruits = generateFruitsRandom(100)
        rvFruits.layoutManager = LinearLayoutManager(this)
        rvFruits.adapter = FruitsAdapter(listFruits)
    }
}