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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.paging.ui

/**
 * Adapter for the list of news.
 */
//class NewsAdapter : PagedListAdapter<News, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return NewsViewHolder.create(parent)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val repoItem = getItem(position)
//        if (repoItem != null) {
//            (holder as NewsViewHolder).bind(repoItem)
//        }
//    }
//
//    companion object {
//        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
//            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
//                    oldItem.title == newItem.title
//
//            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
//                    oldItem == newItem
//        }
//    }
//}
