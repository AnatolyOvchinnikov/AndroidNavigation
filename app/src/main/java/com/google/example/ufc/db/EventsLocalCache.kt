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
import com.google.example.ufc.model.Event
import com.google.example.ufc.model.FightCard
import java.util.concurrent.Executor

class EventsLocalCache(
        private val eventsDao: EventsDao,
        private val ioExecutor: Executor
) {

    fun insert(events: List<Event>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${events.size} events")
            val a = eventsDao.getFightCard1()
            eventsDao.insert(events)
            val b = eventsDao.getFightCard1()
            insertFinished()
        }
    }

    fun insertFightCard(events: List<FightCard>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${events.size} events")
            val a = eventsDao.reposByName1()
            eventsDao.insertFightCard(events)
            val b = eventsDao.reposByName1()
            insertFinished()
        }
    }

    fun getFightCard(eventId: Long) : LiveData<List<FightCard>> {

        ioExecutor.execute {
            val a = eventsDao.getFightCard1()
            val b = a
        }


        return eventsDao.getFightCard(eventId)
    }

    fun reposByName(): DataSource.Factory<Int, Event> {
        return eventsDao.reposByName()
    }

//    fun getNewsById(id: Long): LiveData<News> {
//        return newsDao.getNewsById(id)
//    }
}
