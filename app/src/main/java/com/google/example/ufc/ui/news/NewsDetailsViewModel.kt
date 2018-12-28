package com.google.example.ufc.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.example.ufc.data.NewsRepository
import com.google.example.ufc.model.News

class NewsDetailsViewModel(private val repository: NewsRepository) : ViewModel() {

    fun getCachedNewsDetails(id: Long) : LiveData<News> {
        return repository.getCachedNewsDetails(id)
    }

    class ViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsDetailsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

