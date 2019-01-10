package com.google.example.ufc.ui.fighter


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
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.db.FightersLocalCache
import com.google.example.ufc.model.Fighter
import com.google.example.ufc.ui.sample.SampleActivity
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.concurrent.Executors

class FightersListFragment : Fragment() {
    private lateinit var viewModel: FightersListViewModel
    private val adapter = FightersListAdapter({
        (activity as SampleActivity).changeFab()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighters_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val cache = FightersLocalCache(MainDatabase.getInstance(requireContext()).fightersDao(), Executors.newSingleThreadExecutor())
        val repository = FightersRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, FightersListViewModel.ViewModelFactory(repository)).get(FightersListViewModel::class.java)

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()
    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModel.news.observe(this, Observer<PagedList<Fighter>> {
            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
//        viewModel.networkErrors.observe(this, Observer<String> {
//            Toast.makeText(this, "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
//        })
    }

    fun sort(sortType: String) {
        viewModel.sortList(sortType).observe(this, Observer<PagedList<Fighter>> {
            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
    }
}
