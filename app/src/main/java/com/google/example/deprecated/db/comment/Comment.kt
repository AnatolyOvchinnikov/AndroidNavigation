package com.google.example.deprecated.db.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.example.deprecated.db.user.User

@Entity(foreignKeys = arrayOf(ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("userId"),
        onDelete = CASCADE)))
data class Comment(
        @PrimaryKey(autoGenerate = true) var uid: Int? = null,
        @ColumnInfo(index = true)
        var userId: Int,
        var text: String)