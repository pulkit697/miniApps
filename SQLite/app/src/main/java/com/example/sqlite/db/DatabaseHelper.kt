package com.example.sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.DATABASE

class DatabaseHelper(context:Context):SQLiteOpenHelper(
    context,
    DATABASE,
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TodoTable.CREATE_TABLE  )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}