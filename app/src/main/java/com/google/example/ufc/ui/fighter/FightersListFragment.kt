package com.google.example.ufc.ui.fighter


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.codelabs.mdc.kotlin.shrine.BackdropIconClickListener
import com.google.example.R
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.db.FightersLocalCache
import com.google.example.ufc.model.Fighter
import com.google.example.ufc.ui.sample.SampleActivity
import kotlinx.android.synthetic.main.backdrop_menu_layout.*
import kotlinx.android.synthetic.main.fragment_fighters_list.*
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

        context?.let {
            showBackdrop.setOnClickListener(BackdropIconClickListener(
                    it,
                    list,
                    AccelerateDecelerateInterpolator()))
        }

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            list.background = context?.getDrawable(R.drawable.fighter_details_background_shape)
        }

        filter.setOnClickListener {
            var minVal: Int = 100
            var maxVal : Int = 0

            if(chipAge1.isChecked) {
                minVal = 5
                maxVal = 7
            }

            if(chipAge2.isChecked) {
                minVal = if(minVal < 8) minVal else 8
                maxVal = if(maxVal > 14) maxVal else 14
            }

            if(chipAge3.isChecked) {
                minVal = if(minVal < 15) minVal else 15
                maxVal = if(maxVal > 28) maxVal else 28
            }

            if(chipAge4.isChecked) {
                minVal = if(minVal < 29) minVal else 29
                maxVal = if(maxVal > 40) maxVal else 40
            }

            if(minVal > 0 && maxVal > 0) {
                filterByAge(minVal, maxVal)
            } else {
                clearFilter()
            }

            showBackdrop.performClick()
        }

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

    fun filterByAge(from: Int, to: Int) {
        viewModel.filterByAge(from, to).observe(this, Observer<PagedList<Fighter>> {
            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
    }

    fun clearFilter() {
        viewModel.news.observe(this, Observer<PagedList<Fighter>> {
            Log.d("Activity", "list: ${it?.size}")
//            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
    }
}
