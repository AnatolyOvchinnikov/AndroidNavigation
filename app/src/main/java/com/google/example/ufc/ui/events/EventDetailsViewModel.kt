package com.google.example.ufc.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.example.ufc.data.EventsRepository

class EventDetailsViewModel(private val repository: EventsRepository) : ViewModel() {

//    fun getCachedNewsDetails(id: Long) : LiveData<News> {
//        return repository.getCachedNewsDetails(id)
//    }

    class ViewModelFactory(private val repository: EventsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventDetailsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

