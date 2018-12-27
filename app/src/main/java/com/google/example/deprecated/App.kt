package com.google.example.deprecated

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.example.deprecated.db.AppDatabase

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