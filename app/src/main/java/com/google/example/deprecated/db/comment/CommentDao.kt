package com.google.example.deprecated.db.comment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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