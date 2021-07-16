package com.example.roomretrofit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    val login : String,
    val type : String,
    val url : String ) {
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0 }
