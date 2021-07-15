package com.example.roomretrofit.viewmodel

import com.example.roomretrofit.entity.User

interface UserViewModelCallback {
    fun getListUser(item: ArrayList<User>);
}