package com.google.example.db.user

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.example.App
import com.google.example.db.comment.Comment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Entity
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
            } else {
                null
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}