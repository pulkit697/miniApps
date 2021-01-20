package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btWeb.setOnClickListener{
            val s = eTUrl.text.toString()
            if(s.isNotEmpty())
            {

                Intent().also {
                    it.action = Intent.ACTION_VIEW
                    it.data = Uri.parse("https://$s")
                    startActivity(it)
                }
            }else{
                Toast.makeText(this,"please enter a web address",Toast.LENGTH_SHORT).show()
            }
        }
        btDial.setOnClickListener {
            val s = eTUrl.text.toString()
            if(s.isNotEmpty())
            {
                Intent().also {
                    it.action = Intent.ACTION_DIAL
                    it.data = Uri.parse("tel:$s")
                    startActivity(it)
                }
            }else{
                Toast.makeText(this,"please enter a web address",Toast.LENGTH_SHORT).show()
            }
        }
    }
}