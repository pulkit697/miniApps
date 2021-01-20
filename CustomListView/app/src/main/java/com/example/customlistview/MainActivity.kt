package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val array = arrayOf(Person("Darshan Salecha","all-rounder|captain"),
                Person("Anshika Jain","all-rounder|vice-captain"),
                Person("Harsh Shukla","opener"),
                Person("Ayush Mishra","opener"),
                Person("Pulkit Garg","batsman"),
                Person("Prashant Kartikay","batsman"),
                Person("Shubham Gupta","all-rounder|Striker"),
                Person("Shubham Pathak","bowler"),
                Person("Manav Tyagi","wicket keeper"),
                Person("Sahil Rastogi","bowler"),
                Person("Praful Verma","bowler"))

        lvPeople.adapter = CustomAdapter(this,array)

    }
}