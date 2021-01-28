package com.example.fileread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btWrite.setOnClickListener {
            val dataDirectory = ContextCompat.getDataDir(this)
            val myFile = File(dataDirectory, "myfile.txt")
            myFile.writeText(etWrite.text.toString())
        }
        btRead.setOnClickListener {
            val dataDirectory = ContextCompat.getDataDir(this)
            val myFile = File(dataDirectory, "myfile.txt")
            try {
                tvRead.text = myFile.readText() /*this works only up to 2GB of file */
            } catch (e: FileNotFoundException){
                Toast.makeText(this,"Please write some data in file first!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}