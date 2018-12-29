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
        @field:SerializedName("fighter1_Id") val fighter1_Id: Long,
        @field:SerializedName("fighter1_name") val fighter1_name: String,
        @field:SerializedName("fighter2_Id") val fighter2_Id: Long,
        @field:SerializedName("fighter2_name") val fighter2_name: String,
        @field:SerializedName("eventId") val eventId: Long
)
