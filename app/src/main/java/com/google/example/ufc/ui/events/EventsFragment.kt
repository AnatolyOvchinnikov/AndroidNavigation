package com.google.example.ufc.ui.events

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.example.R
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.EventsRepository
import com.google.example.ufc.db.EventsLocalCache
import com.google.example.ufc.model.Event
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.concurrent.Executors

class EventsFragment : Fragment() {
    private lateinit var viewModel: EventsViewModel
    private val adapter = EventsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cache = EventsLocalCache(MainDatabase.getInstance(requireContext()).eventsDao(), Executors.newSingleThreadExecutor())
        val repository = EventsRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, EventsViewModel.ViewModelFactory(repository)).get(EventsViewModel::class.java)

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()
    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModel.events.observe(this, Observer<PagedList<Event>> {
            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
//        viewModel.networkErrors.observe(this, Observer<String> {
//            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
//        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
