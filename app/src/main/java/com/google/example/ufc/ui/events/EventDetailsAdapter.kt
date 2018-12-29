package com.google.example.ufc.ui.events

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.example.ufc.model.FightCard

class EventDetailsAdapter(val cards: List<FightCard>) : RecyclerView.Adapter<EventDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailsViewHolder {
        return EventDetailsViewHolder.create(parent)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: EventDetailsViewHolder, position: Int) {
        val repoItem = cards.get(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }
}