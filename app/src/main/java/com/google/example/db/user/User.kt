package com.google.example.db.user

import android.annotation.SuppressLint
import android.arch.persistence.room.*
import com.google.example.App
import com.google.example.db.comment.Comment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Entity(indices = arrayOf(Index("uid"), Index(value = ["first_name", "last_name"])))
data class User (@PrimaryKey(autoGenerate = true) var uid: Int? = null,
            @ColumnInfo(name = "first_name") var firstName: String?,
            @ColumnInfo(name = "last_name") var lastName: String?) {

    @Ignore
    private val db = App.getInstance().getDatabase()

    @SuppressLint("CheckResult")
    fun getComments(): Single<List<Comment>>? {
        return Single.create<List<Comment>> {
            if(uid != null) {
                it.onSuccess(db.commentDao().getCommentsByUserId(uid!!))
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

//    @Relation(parentColumn = "uid", entityColumn = "userId")
//    lateinit var userCommentsList: List<Comment>
}

class TestEntity() {
    @Embedded
    lateinit var user: User

    @Relation(parentColumn = "uid", entityColumn = "userId")
    lateinit var userCommentsList: List<Comment>
}