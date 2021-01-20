package com.example.explicitintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sending data to child intent
        btAct1.setOnClickListener {
            val s = etAct1.text.toString()
            if(s.isNotEmpty()) {
                Intent(this, MainActivity2::class.java).also {
                    it.putExtra(KEY_DATA, s)
                    startActivityForResult(it, REQUEST_CODE)
                }
            }else{
                Toast.makeText(this,"Please enter a data to be sent!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //getting data from child intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE && data!=null) {
            tvAct1.text = data.getStringExtra(KEY_RETURNED_DATA)
        }
    }
}