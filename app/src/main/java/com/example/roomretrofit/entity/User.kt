package com.example.roomretrofit.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var idUser: Long = 0,
    val login: String,
    val type: String,
    val url: String) : Parcelable
