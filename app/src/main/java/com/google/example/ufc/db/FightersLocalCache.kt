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

package com.google.example.ufc.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.google.example.ufc.model.Fighter
import java.util.concurrent.Executor

class FightersLocalCache(
        private val fightersDao: FightersDao,
        private val ioExecutor: Executor
) {

    fun insert(fighter: Fighter, insertFinished: () -> Unit) {
        ioExecutor.execute {
            fightersDao.insertFighter(fighter)
            insertFinished()
        }
    }

    fun insert(news: List<Fighter>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${news.size} news")
            fightersDao.insert(news)
            insertFinished()
        }
    }

    fun getFighterProfile(id: Long) : LiveData<Fighter> {
        return fightersDao.getFighterProfile(id)
    }

    fun getFightersList(): DataSource.Factory<Int, Fighter> {
        return fightersDao.getFightersList()
    }

    fun sort(sortType: String): DataSource.Factory<Int, Fighter> {
        return fightersDao.sortRaw(SimpleSQLiteQuery("SELECT * FROM fighter ORDER BY " + sortType))
    }
}
