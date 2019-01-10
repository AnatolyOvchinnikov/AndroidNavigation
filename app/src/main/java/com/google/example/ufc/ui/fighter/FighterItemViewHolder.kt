package com.google.example.ufc.ui.fighter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.example.R
import com.google.example.ufc.model.Fighter

class FighterItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.newsTitle)

    private var news: Fighter? = null
    private lateinit var clickListener: () -> Unit

    init {
        view.setOnClickListener {
            val directions = news?.id?.let { it1 -> FightersListFragmentDirections.actionFightersListFragmentToFighterProfileFragment().setId(it1) }
            directions?.let { it1 -> it.findNavController().navigate(it1) }
            clickListener()
        }
    }

    fun bind(news: Fighter?, clickListener: () -> Unit) {
        this.news = news
        this.clickListener = clickListener
        name.text = news?.name
    }

    companion object {
        fun create(parent: ViewGroup): FighterItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_view_item, parent, false)
            return FighterItemViewHolder(view)
        }
    }
}
