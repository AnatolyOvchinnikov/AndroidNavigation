package com.google.example.ufc.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.example.ufc.data.EventsRepository
import com.google.example.ufc.model.FightCard

class EventDetailsViewModel(private val repository: EventsRepository) : ViewModel() {

//    fun getCachedNewsDetails(id: Long) : LiveData<News> {
//        return repository.getCachedNewsDetails(id)
//    }

    fun getFightCard(eventId: Long) : LiveData<List<FightCard>> = repository.getFightCard(eventId)

    fun loadFightCard(eventId: Long) = repository.loadFightCard(eventId)

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

