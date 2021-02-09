package com.example.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.User

@Database(entities = [User::class],version = 1)
abstract class RoomDB:RoomDatabase() {

    abstract fun getDao() : RoomDao

}