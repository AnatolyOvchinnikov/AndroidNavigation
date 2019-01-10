package com.google.example.ufc.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.google.example.ufc.api.Api
import com.google.example.ufc.api.loadFighters
import com.google.example.ufc.db.FightersLocalCache
import com.google.example.ufc.model.Fighter

class FightersBoundaryCallback(
        private val service: Api,
        private val cache: FightersLocalCache
) : PagedList.BoundaryCallback<Fighter>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Fighter) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
//        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true

        loadFighters(service, {
            fighters ->
            cache.insert(fighters) {
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, {
            isRequestInProgress = false
        })
    }
}