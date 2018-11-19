package com.google.example

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider



class NameViewModel(val arg: String) : ViewModel() {

    // Create a LiveData with a String
    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val liveData1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val liveData2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mediatorLiveData: MediatorLiveData<String> by lazy {
        MediatorLiveData<String>()
    }

    init {
        mediatorLiveData.addSource(liveData1, {
            mediatorLiveData.value = it
        })

        mediatorLiveData.addSource(liveData2, {
            mediatorLiveData.value = it
        })
    }

    fun checkArg() {
        val arg = this.arg
    }

    // Rest of the ViewModel...

    override fun onCleared() {
        super.onCleared()
    }

    class NameViewFactory(val arg: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NameViewModel(arg) as T
        }
    }
}