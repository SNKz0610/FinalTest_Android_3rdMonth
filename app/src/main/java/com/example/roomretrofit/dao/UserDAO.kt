package com.example.roomretrofit.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomretrofit.entity.User

@Dao
interface UserDAO {
    @Insert
    fun addListUsers(arrUsers : List<User>)

    @Query("SELECT * FROM user")
    fun getAllUsers() : LiveData<List<User>>

    @Update
    suspend fun updateUsers(user : User)

    @Delete
    suspend fun deleteUser(user : User)
}