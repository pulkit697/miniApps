package com.example.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    var title:String,
    var description:String,
    var category:String,
    var date:Long,
    var time :Long,
    var isDone:Int = -1,
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0
)
