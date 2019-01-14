package com.google.example.ufc.ui.fighter

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.model.Fighter

class FighterProfileViewModel(private val repository: FightersRepository) : ViewModel() {

    val name = ObservableField<String>()
    val age = ObservableInt()

    lateinit var fighterData: LiveData<Fighter>

    fun loadFighterProfile(id: Long) {
        repository.loadFighterProfile(id)
        val fighterProfile = repository.getFighterProfile(id)
        fighterData = Transformations.map(fighterProfile, {
            it?.let {
                name.set(it.name)
                age.set(it.age)
            }
            it
        })
    }

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
