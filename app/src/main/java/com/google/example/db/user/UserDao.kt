package com.google.example.db.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("select * from User where uid = :uid")
    fun getUser(uid: Int): User

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name is :first AND " +
            "last_name is :last LIMIT 1")
    fun findByName(first: String, last: String): User

//    @Query("select * from User join Comment on User.uid = Comment.userId where User.uid = :userId")
//    fun getComments(userId: Int): List<Comment>

    @Insert
    fun insertAll(vararg users: User)

    @Insert
    fun insert(user: User): Long

    @Insert
    fun insertArray(users: List<User>)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAllFlowable(): Flowable<List<User>>

    @Query("select * from User where uid = :uid")
    fun getUserFlowable(uid: Int): Flowable<User>

    @Query("select * from User where uid = :uid")
    fun getUserSingle(uid: Int): Single<User>

    @Query("select * from User where uid = :uid")
    fun getUserMaybe(uid: Int): Maybe<User>

    @Query("SELECT * FROM user")
    fun getUsersWithComments(): List<TestEntity>
}