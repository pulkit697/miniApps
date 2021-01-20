package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_fruit.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvFruits.adapter = ArrayAdapter(
            this,
            R.layout.list_item_fruit,
            R.id.tvFruit,
            arrayOf("Apple","Guava","Pomegranate","Peach",
                "melon","Watermelon","Grapes","banana","kiwi",
                "Strawberry","orange","Tangerine","Apple","Guava","Pomegranate","Peach",
                "melon","Watermelon","Grapes","banana","kiwi",
                "Strawberry","orange","Tangerine")
        )

        lvFruits.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(parent.context,"List item ${position+1} i.e. ${view.tvFruit.text} is clicked!",Toast.LENGTH_SHORT).show()
        }
    }
}