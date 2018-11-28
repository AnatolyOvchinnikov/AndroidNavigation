package com.google.example

import android.app.Application
import android.arch.persistence.room.Room
import com.google.example.db.AppDatabase

class App : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .build()
    }


    fun getDatabase(): AppDatabase {
        return database
    }

    companion object {
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }
}