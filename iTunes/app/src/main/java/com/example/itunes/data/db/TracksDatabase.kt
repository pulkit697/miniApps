package com.example.itunes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunes.data.model.SingleTrack
import com.example.itunes.utils.DB_NAME

@Database(entities = [SingleTrack::class],version = 1,exportSchema = false)
abstract class TracksDatabase:RoomDatabase() {
    abstract fun getRoomDao():SavingTracksDao
    companion object {
        @Volatile
        private var INSTANCE: TracksDatabase? = null

        fun getInstance(context: Context): TracksDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, TracksDatabase::class.java,
                            DB_NAME).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}