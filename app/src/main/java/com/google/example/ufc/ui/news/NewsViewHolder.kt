package com.google.example.ufc.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.example.R
import com.google.example.ufc.model.News

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.newsTitle)

    private var news: News? = null

    init {
        view.setOnClickListener {
            val directions = news?.id?.let { it1 -> NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment().setId(it1) }
            directions?.let { it1 -> it.findNavController().navigate(it1) }
        }
    }

    fun bind(news: News?) {
        this.news = news
        name.text = news?.title
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_view_item, parent, false)
            return NewsViewHolder(view)
        }
    }
}
