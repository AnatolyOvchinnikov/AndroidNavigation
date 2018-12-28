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

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.example.ufc.model.Event

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Event>)

    @Query("SELECT * FROM events")
    fun reposByName(): DataSource.Factory<Int, Event>

//    @Query("SELECT * FROM news WHERE id = :id")
//    fun getNewsById(id: Long): LiveData<News>
}