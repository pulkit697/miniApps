package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun add(view: View)
    {
        if(etFirst.text.isNotEmpty() && etSecond.text.isNotEmpty())
        {
            val answer = etFirst.text.toString().toDouble() + etSecond.text.toString().toDouble()
            val result = "Answer: $answer"
            tvAnswer.text = result
        }
    }
    fun sub(view: View)
    {
        if(etFirst.text.isNotEmpty() && etSecond.text.isNotEmpty())
        {
            val answer = etFirst.text.toString().toDouble() - etSecond.text.toString().toDouble()
            val result = "Answer: $answer"
            tvAnswer.text = result
        }
    }
    fun mul(view: View)
    {
        if(etFirst.text.isNotEmpty() && etSecond.text.isNotEmpty())
        {
            val answer = etFirst.text.toString().toDouble() * etSecond.text.toString().toDouble()
            val result = "Answer: $answer"
            tvAnswer.text = result
        }
    }
    fun div(view: View)
    {
        if(etFirst.text.isNotEmpty() && etSecond.text.isNotEmpty())
        {
            val answer = etFirst.text.toString().toDouble() / etSecond.text.toString().toDouble()
            val result = "Answer: $answer"
            tvAnswer.text = result
        }
    }
}