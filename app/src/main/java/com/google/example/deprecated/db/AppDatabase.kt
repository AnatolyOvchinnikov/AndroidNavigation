package com.google.example.deprecated.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.example.deprecated.db.comment.Comment
import com.google.example.deprecated.db.comment.CommentDao
import com.google.example.deprecated.db.user.User
import com.google.example.deprecated.db.user.UserDao

@Database(entities = arrayOf(User::class, Comment::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}