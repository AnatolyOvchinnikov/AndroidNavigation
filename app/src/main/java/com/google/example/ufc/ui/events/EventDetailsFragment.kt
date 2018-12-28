package com.google.example.ufc.ui.events


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.example.R
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.EventsRepository
import com.google.example.ufc.db.EventsLocalCache
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val cache = EventsLocalCache(MainDatabase.getInstance(requireContext()).eventsDao(), Executors.newSingleThreadExecutor())
        val repository = EventsRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, EventDetailsViewModel.ViewModelFactory(repository)).get(EventDetailsViewModel::class.java)

//        val id = NewsDetailsFragmentArgs.fromBundle(arguments).id
//        val news = viewModel.getCachedNewsDetails(id)
//        news.observe(this, Observer{
//            newsDescription.setText(it.description)
//        })
    }
}
