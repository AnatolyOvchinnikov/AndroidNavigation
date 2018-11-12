package com.google.example

import android.arch.lifecycle.LiveData

class UserLiveData: LiveData<User>() {
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}