package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(toDo: ToDo):Long

    @Query("delete from ToDo where id =:id")
    suspend fun delete(id:Long)

    @Query("Update ToDo set isDone = 1 where id = :id")
    suspend fun finishTask(id:Long)

    @Query("Select * from ToDo")
    fun getAll():LiveData<List<ToDo>>

    @Query("Select * from ToDo where isDone==-1")
    fun getIncompleteTasks():LiveData<List<ToDo>>

}