package com.google.example

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.google.example.db.AppDatabase

class App : Application() {
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2)
                .build()
    }

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val isOpen = database.isOpen
            if(isOpen) {
                database.execSQL("CREATE INDEX `index_first_name_last_name` ON `User` ( `first_name`, `last_name` )")
            }
        }

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