package com.google.example.ufc.ui.fighter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.model.Fighter

class FightersListViewModel(private val repository: FightersRepository) : ViewModel() {

    val news: LiveData<PagedList<Fighter>> = repository.search()

    fun sortList(sortType: String): LiveData<PagedList<Fighter>> = repository.sort(sortType)

    class ViewModelFactory(private val repository: FightersRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FightersListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FightersListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

