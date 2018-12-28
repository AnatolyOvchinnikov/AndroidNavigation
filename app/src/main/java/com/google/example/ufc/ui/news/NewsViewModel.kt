package com.google.example.ufc.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.google.example.ufc.data.NewsRepository
import com.google.example.ufc.model.News

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val news: LiveData<PagedList<News>> = repository.search()

    class ViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

