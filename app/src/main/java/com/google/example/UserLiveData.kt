package com.google.example

import android.arch.lifecycle.LiveData
import com.google.example.db.user.User

class UserLiveData: LiveData<User>() {
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}