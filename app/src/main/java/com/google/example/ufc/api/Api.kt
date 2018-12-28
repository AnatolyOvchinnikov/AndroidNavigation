/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF Any KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.example.ufc.api

import android.util.Log
import com.google.example.ufc.model.Event
import com.google.example.ufc.model.News
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val TAG = "GithubService"
private const val IN_QUALIFIER = "in:name,description"

fun loadNews(service: Api,
        onSuccess: (repos: List<News>) -> Unit,
             onError: (error: String) -> Unit
) {
    service.loadNews().enqueue(
            object : Callback<List<News>> {
                override fun onFailure(call: Call<List<News>>?, t: Throwable) {
                    Log.d(TAG, "fail to get data")
//                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                    call: Call<List<News>>?,
                    response: Response<List<News>>
                ) {
                    Log.d(TAG, "got a response $response")
                    if (response.isSuccessful) {
                        val news = response.body() ?: emptyList()
                        onSuccess(news)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}

fun loadEvents(service: Api,
               onSuccess: (repos: List<Event>) -> Unit,
               onError: (error: String) -> Unit
) {
    service.loadEvents().enqueue(
            object : Callback<List<Event>> {
                override fun onFailure(call: Call<List<Event>>?, t: Throwable) {
                    Log.d(TAG, "fail to get data")
//                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<List<Event>>?,
                        response: Response<List<Event>>
                ) {
                    Log.d(TAG, "got a response $response")
                    if (response.isSuccessful) {
                        val events = response.body() ?: emptyList()
                        onSuccess(events)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}

/**
 * Github API communication setup via Retrofit.
 */
interface Api {
    /**
     * Get repos ordered by stars.
     */
    @GET("news.json")
    fun loadNews(): Call<List<News>>

    @GET("events.json")
    fun loadEvents(): Call<List<Event>>

    companion object {
        private const val BASE_URL = "https://ufc-app-f80fe.firebaseio.com/"

        fun create(): Api {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
        }
    }
}