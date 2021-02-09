package com.example.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name:String,
    val address:String,
    val number: Long,
    val age:Int,
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L
)
