package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.DB_NAME

@Database(entities = [ToDo::class],version = 1)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getDao():ToDoDao
    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? =null
        fun getInstance(context: Context):TodoDatabase
        {
            synchronized(this)
            {
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}