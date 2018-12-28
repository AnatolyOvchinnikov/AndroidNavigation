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

package com.google.example.ufc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) @field:SerializedName("id") val id: Long,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("date") val date: Long
)

@Entity(tableName = "fight_card",
        foreignKeys = arrayOf(ForeignKey(
                entity = Event::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("eventId"),
                onDelete = ForeignKey.CASCADE)))
data class FightCard(
        @PrimaryKey(autoGenerate = true) @field:SerializedName("id") val id: Long,
        @field:SerializedName("player1Id") val player1Id: Long,
        @field:SerializedName("player2Id") val player2Id: Long,
        @field:SerializedName("eventId") val eventId: Long
)

data class EventResponse(
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("description") val description: String,
        @field:SerializedName("fight_card") val fightCard: List<FightCardResponse>
)

data class FightCardResponse(
        @field:SerializedName("id") val id: Long? = null,
        @field:SerializedName("eventId") val title: String,
        @field:SerializedName("player1") val player1: PlayerResponse,
        @field:SerializedName("player2") val player2: PlayerResponse
)

data class PlayerResponse(@field:SerializedName("userId") val id: Long,
                          @field:SerializedName("name") val name: String)
