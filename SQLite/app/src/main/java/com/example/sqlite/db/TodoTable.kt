package com.example.sqlite.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.sqlite.TABLE_NAME
import com.example.sqlite.model.Todo

object TodoTable {

     object Column{
         const val ID = "id"
         const val TASK = "task"
         const val DONE = "done"
     }
      val CREATE_TABLE = """CREATE TABLE IF NOT EXISTS $TABLE_NAME
          (
          ${Column.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
          ${Column.TASK} TEXT,
          ${Column.DONE} BOOLEAN
          );
      """.trimMargin()

    fun insertToDo(db:SQLiteDatabase , todo: Todo)
    {
        val row = ContentValues().apply {
            put(Column.TASK,todo.task)
            put(Column.DONE,todo.done)
        }
        db.insert(TABLE_NAME,null,row)
    }

    fun getAllToDo(db: SQLiteDatabase) : ArrayList<Todo>
    {
        val cursor = db.query(TABLE_NAME,
        arrayOf(Column.TASK,Column.DONE),
        null,null,null,null,null)

        val list = ArrayList<Todo>()
        while (cursor.moveToNext())
        {
            val todo = Todo(cursor.getString(0),cursor.getInt(1) != 0)
            list.add(todo)
        }
        return list
    }

}