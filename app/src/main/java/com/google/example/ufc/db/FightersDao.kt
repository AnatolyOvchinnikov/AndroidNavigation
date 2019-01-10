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

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.google.example.ufc.model.Fighter

@Dao
interface FightersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFighter(user: Fighter)

    @Query("SELECT * FROM fighter WHERE id = :id")
    fun getFighterProfile(id: Long): LiveData<Fighter>

    @Query("SELECT * FROM fighter")
    fun getFightersList(): DataSource.Factory<Int, Fighter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<Fighter>)

    @RawQuery(observedEntities = [Fighter::class])
    fun sortRaw(sortQuery: SupportSQLiteQuery): DataSource.Factory<Int, Fighter>

    @Query("SELECT * FROM fighter WHERE age BETWEEN :from AND :to")
    fun filterByAge(from: Int, to: Int): DataSource.Factory<Int, Fighter>
}