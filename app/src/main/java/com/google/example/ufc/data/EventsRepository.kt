package com.google.example.ufc.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.example.ufc.api.Api
import com.google.example.ufc.db.EventsLocalCache
import com.google.example.ufc.model.Event

class EventsRepository(private val service: Api,
                       private val cache: EventsLocalCache) {

    fun loadEvents(): LiveData<PagedList<Event>> {
        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByName()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = EventsBoundaryCallback(service, cache)
//        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return data
    }

//    fun getCachedNewsDetails(id: Long) : LiveData<News> {
//        return cache.getNewsById(id)
//    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}