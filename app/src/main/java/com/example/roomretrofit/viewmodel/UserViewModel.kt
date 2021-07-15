package com.example.roomretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofit.entity.User
import com.example.roomretrofit.service.UserService
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {
    var callBack: UserViewModelCallback? = null
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(UserService::class.java)


     fun getAllUser() {
        var listGet: ArrayList<User> = ArrayList()
        viewModelScope.async {
            val result = service.getAllUser()
            for (i in 0 until result.size) {
                listGet.add(result[i])
            }
//            Log.d("Arr", listGet.toString())
            callBack?.getListUser(listGet);
        }
    }
}