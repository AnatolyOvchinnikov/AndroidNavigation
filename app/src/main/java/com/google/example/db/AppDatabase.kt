package com.google.example.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.google.example.db.comment.Comment
import com.google.example.db.comment.CommentDao
import com.google.example.db.user.User
import com.google.example.db.user.UserDao

@Database(entities = arrayOf(User::class, Comment::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}