package com.google.example.db.comment

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment")
    fun getAll(): List<Comment>

    @Query("SELECT * FROM comment WHERE userId = (:userId)")
    fun getCommentsByUserId(userId: Int): List<Comment>

    @Insert
    fun insertAll(vararg comments: Comment)

    @Delete
    fun delete(comment: Comment)
}