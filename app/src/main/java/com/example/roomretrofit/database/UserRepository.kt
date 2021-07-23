package com.example.roomretrofit.database

import android.app.Application
import android.content.SyncContext
import com.example.roomretrofit.entity.User
import kotlinx.coroutines.InternalCoroutinesApi

class UserRepository (application: Application) {
    @InternalCoroutinesApi
    val database = UserDataBase.getInstance(application)

    @InternalCoroutinesApi
    fun getAllUsers() =database.userDAO().getAllUsers()

    @InternalCoroutinesApi
    suspend fun addListPeople(arrUsers : List<User>) = database.userDAO().addListUsers(arrUsers)

    @InternalCoroutinesApi
    suspend fun updateUser(user : User) = database.userDAO().updateUsers(user)

    @InternalCoroutinesApi
    suspend fun deleteUser(user : User) = database.userDAO().deleteUser(user)
}