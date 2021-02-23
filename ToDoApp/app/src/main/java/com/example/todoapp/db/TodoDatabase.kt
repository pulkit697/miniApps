package com.example.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class],version = 1)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getDao():ToDoDao
}