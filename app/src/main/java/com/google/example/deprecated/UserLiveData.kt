package com.google.example.deprecated

import androidx.lifecycle.LiveData
import com.google.example.deprecated.db.user.User

class UserLiveData: LiveData<User>() {
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}