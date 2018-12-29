package com.google.example.ufc.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.example.R
import com.google.example.ufc.model.FightCard


class EventDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val fighter1 = view.findViewById<TextView>(R.id.fighter1)
    private val fighter2 = view.findViewById<TextView>(R.id.fighter2)
//    var card: ObservableField<FightCard> = ObservableField()
    private var card: FightCard? = null

//    val t = TestView("A", "B")

    init {
        view.setOnClickListener {
//            val directions = event?.id?.let { it1 -> EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment().setId(it1) }
//            directions?.let { it1 -> it.findNavController().navigate(it1) }
        }
    }

    fun bind(card: FightCard?) {
        this.card = card
        fighter1.text = card?.fighter1_name
        fighter2.text = card?.fighter2_name
//        this.card.set(card)
//        name1.set(card?.fighter1_name)
//        name2.set(card?.fighter2_name)

//        fighter1.text = card?.fighter1_name
//        fighter2.text = card?.fighter2_name
    }

    companion object {
        fun create(parent: ViewGroup): EventDetailsViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fight_card_view_item, parent, false)
            return EventDetailsViewHolder(view)
        }
    }
}

//class TestView(val str1: String, val str2: String) {
//    val name1: ObservableField<String> = ObservableField()
//    val name2: ObservableField<String> = ObservableField()
//
//    init {
//        name1.set(str1)
//        name2.set(str2)
//    }
//}
