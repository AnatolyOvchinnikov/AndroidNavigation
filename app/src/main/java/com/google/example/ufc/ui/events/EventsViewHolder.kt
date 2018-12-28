package com.google.example.ufc.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.example.R
import com.google.example.ufc.model.Event

class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.newsTitle)

    private var event: Event? = null

    init {
        view.setOnClickListener {
            val directions = event?.id?.let { it1 -> EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment().setId(it1) }
            directions?.let { it1 -> it.findNavController().navigate(it1) }
        }
    }

    fun bind(event: Event?) {
        this.event = event
        name.text = event?.title
    }

    companion object {
        fun create(parent: ViewGroup): EventsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_view_item, parent, false)
            return EventsViewHolder(view)
        }
    }
}
