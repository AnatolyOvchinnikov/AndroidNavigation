package com.google.example.ufc.ui.events


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.example.R
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.EventsRepository
import com.google.example.ufc.db.EventsLocalCache
import kotlinx.android.synthetic.main.fragment_event_details.*
import java.util.concurrent.Executors

class EventDetailsFragment : Fragment() {
    private lateinit var viewModel: EventDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cache = EventsLocalCache(MainDatabase.getInstance(requireContext()).eventsDao(), Executors.newSingleThreadExecutor())
        val repository = EventsRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, EventDetailsViewModel.ViewModelFactory(repository)).get(EventDetailsViewModel::class.java)

        val id = EventDetailsFragmentArgs.fromBundle(arguments).id
        viewModel.loadFightCard(id)
        viewModel.getFightCard(id).observe(this, Observer { it ->
            it?.let { it1 ->
                val adapter = EventDetailsAdapter(it1)
                list.adapter = adapter
            }
        })
    }
}
