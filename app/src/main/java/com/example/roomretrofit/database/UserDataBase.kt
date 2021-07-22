package com.example.roomretrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomretrofit.dao.UserDAO
import com.example.roomretrofit.entity.User
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    @InternalCoroutinesApi
    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase =
            INSTANCE ?: kotlinx.coroutines.internal.synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java, "user_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}