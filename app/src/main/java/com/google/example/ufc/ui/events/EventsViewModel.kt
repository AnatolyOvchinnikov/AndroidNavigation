package com.google.example.ufc.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.google.example.ufc.data.EventsRepository
import com.google.example.ufc.model.Event

class EventsViewModel(private val repository: EventsRepository) : ViewModel() {

//    fun getCachedNewsDetails(id: Long) : LiveData<News> {
//        return repository.getCachedNewsDetails(id)
//    }

    val events: LiveData<PagedList<Event>> = repository.loadEvents()

    class ViewModelFactory(private val repository: EventsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}