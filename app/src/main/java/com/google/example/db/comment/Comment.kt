package com.google.example.db.comment

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.google.example.db.user.User

@Entity(foreignKeys = arrayOf(ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("userId"),
        onDelete = CASCADE)))
data class Comment(
        @PrimaryKey(autoGenerate = true) var uid: Int,
        @ColumnInfo(index = true)
        var userId: Int,
        var text: String)