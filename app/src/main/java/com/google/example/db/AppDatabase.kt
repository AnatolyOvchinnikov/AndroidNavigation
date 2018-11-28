package com.google.example.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.google.example.db.user.User
import com.google.example.db.user.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}