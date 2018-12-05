package com.google.example.db.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("select * from User where uid = :uid")
    fun getUser(uid: Int): User

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

//    @Query("select * from User join Comment on User.uid = Comment.userId where User.uid = :userId")
//    fun getComments(userId: Int): List<Comment>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}