package com.google.example.deprecated.db.user

import android.annotation.SuppressLint
import androidx.room.*
import com.google.example.deprecated.App
import com.google.example.deprecated.db.comment.Comment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@Entity(indices = arrayOf(Index("uid"), Index(value = ["first_name", "last_name"])))
@TypeConverters(HobbiesConverter::class)
data class User (@PrimaryKey(autoGenerate = true) var uid: Int? = null,
                 @ColumnInfo(name = "first_name") var firstName: String?,
                 @ColumnInfo(name = "last_name") var lastName: String?,
                 var hobbies: List<String>? = null) {

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

    @Ignore
    val uid2: String = generateUniqueKey()

    @Ignore
    fun generateUniqueKey(): String {
        return Random().nextInt(100000).toString()
    }
}

class TestEntity() {
    @Embedded
    lateinit var user: User

    @Relation(parentColumn = "uid", entityColumn = "userId")
    lateinit var userCommentsList: List<Comment>
}

class HobbiesConverter {
    @TypeConverter
    fun fromHobbies(hobbies: List<String>): String {
        return hobbies.joinToString()
    }

    @TypeConverter
    fun toHobbies(data: String): List<String> {
        return data.split(",").map { it.trim() }
    }

}