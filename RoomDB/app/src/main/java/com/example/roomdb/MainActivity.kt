package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.roomdb.db.RoomDB
import com.example.roomdb.util.DATABASE_NAME
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val database by lazy{
        Room.databaseBuilder(this,RoomDB::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSave.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {database.getDao().insert(User("Pulkit Garg","Noida",98765432345,20)) }
        }

            database.getDao().getValidUsers(18).observe(this, Observer { list->
                if (list.isNotEmpty()) {
                    with(list[0])
                    {
                        tvName.text = name + id
                        tvAddress.text = address
                        tvNumber.text = number.toString()
                        tvAge.text = age.toString()
                    }
                }
            })
    }
}