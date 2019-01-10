package com.google.example.ufc.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    fun sort(sortType: String): LiveData<PagedList<Fighter>> {
        // Get data source factory from the local cache
        val dataSourceFactory = cache.sort(sortType)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
//        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .build()

        // Get the network errors exposed by the boundary callback
        return data
    }

    fun search(): LiveData<PagedList<Fighter>> {
        // Get data source factory from the local cache
        val dataSourceFactory = cache.getFightersList()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = FightersBoundaryCallback(service, cache)
//        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return data
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
