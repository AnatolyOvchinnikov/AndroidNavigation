package com.google.example.ufc.data

import androidx.lifecycle.LiveData
import com.google.example.ufc.api.Api
import com.google.example.ufc.api.loadFighterProfile
import com.google.example.ufc.db.FightersLocalCache
import com.google.example.ufc.model.Fighter

/**
 * Repository class that works with local and remote data sources.
 */
class FightersRepository(
    private val service: Api,
    private val cache: FightersLocalCache
) {
    fun loadFighterProfile(id: Long) {
        loadFighterProfile(service, id, { item ->
            cache.insert(item) {

            }
        }, {

        })
    }

    fun getFighterProfile(id: Long) : LiveData<Fighter> = cache.getFighterProfile(id)
}
