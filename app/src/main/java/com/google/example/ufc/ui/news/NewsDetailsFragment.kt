package com.google.example.ufc.ui.news


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
import com.google.example.ufc.data.NewsRepository
import com.google.example.ufc.db.NewsLocalCache
import kotlinx.android.synthetic.main.fragment_news_details.*
import java.util.concurrent.Executors

class NewsDetailsFragment : Fragment() {
    private lateinit var viewModel: NewsDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cache = NewsLocalCache(MainDatabase.getInstance(requireContext()).newsDao(), Executors.newSingleThreadExecutor())
        val repository = NewsRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, NewsDetailsViewModel.ViewModelFactory(repository)).get(NewsDetailsViewModel::class.java)

        val id = NewsDetailsFragmentArgs.fromBundle(arguments).id
        val news = viewModel.getCachedNewsDetails(id)
        news.observe(this, Observer{
            newsDescription.setText(it.description)
        })
    }
}
