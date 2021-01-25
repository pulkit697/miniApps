package com.example.fragmentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()
        bundle.putString("KEY","Pulkit")
        val fragment = SecondFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frag_container,FirstFragment())
            .commitNow()

        btSwitch.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frag_container,fragment)
                .commitNow()
        }

    }
}