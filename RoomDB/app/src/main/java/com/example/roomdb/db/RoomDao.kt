package com.example.roomdb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdb.User

@Dao
interface RoomDao {

    @Insert
    suspend fun insert(user:User)

    @Delete
    fun delete(user:User)

    @Query("SELECT * from User where age>=:age")
    fun getValidUsers(age:Int):LiveData<List<User>>
}