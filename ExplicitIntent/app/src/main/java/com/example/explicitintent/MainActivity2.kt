package com.example.explicitintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //getting data from the parent intent
        val i =intent
        tvAct2.text = i.getStringExtra(KEY_DATA)!!

        //sending data to parent intent
        btAct2.setOnClickListener {
            val s = etAct2.text.toString()
            if(s.isNotEmpty()){
                val returningIntent = Intent()
                    returningIntent.also {
                    it.putExtra(KEY_RETURNED_DATA,s)
                }
                setResult(RESULT_OK,returningIntent)
                finish()
            }else{
                Toast.makeText(this,"Please enter a data to be returned!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}