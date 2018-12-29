/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.paging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.example.ufc.db.EventsDao
import com.google.example.ufc.db.FightersDao
import com.google.example.ufc.db.NewsDao
import com.google.example.ufc.model.Event
import com.google.example.ufc.model.FightCard
import com.google.example.ufc.model.Fighter
import com.google.example.ufc.model.News

@Database(
        entities = [News::class, Event::class, FightCard::class, Fighter::class],
        version = 1
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun eventsDao(): EventsDao
    abstract fun fightersDao(): FightersDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MainDatabase::class.java, "database.db")
                        .build()
    }
}
