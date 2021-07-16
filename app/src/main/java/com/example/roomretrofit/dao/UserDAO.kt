package com.example.roomretrofit.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomretrofit.entity.User

@Dao
interface UserDAO {
    @Insert
    fun addListUsers(arrUsers : List<User>)

    @Query("SELECT * FROM user")
    fun getAllUsers() : LiveData<List<User>>
}