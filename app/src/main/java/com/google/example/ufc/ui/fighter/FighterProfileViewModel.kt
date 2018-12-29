package com.google.example.ufc.ui.fighter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.model.Fighter

class FighterProfileViewModel(private val repository: FightersRepository) : ViewModel() {

    fun getFighterProfile(id: Long) : LiveData<Fighter> = repository.getFighterProfile(id)

    fun loadFighterProfile(id: Long) = repository.loadFighterProfile(id)

    class ViewModelFactory(private val repository: FightersRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FighterProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FighterProfileViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
