package com.example.roomretrofit.service

import androidx.lifecycle.LiveData
import com.example.roomretrofit.entity.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getAllUser() : ArrayList<User>
}