package com.google.example.ufc.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.example.R
import com.google.example.ufc.api.loadNews
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
//    private val adapter = NewsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)

        initAdapter()

        loadNews()
    }

    private fun initAdapter() {
//        list.adapter = adapter
//        viewModel.repos.observe(this, Observer<PagedList<Repo>> {
//            Log.d("Activity", "list: ${it?.size}")
////            showEmptyList(it?.size == 0)
//            adapter.submitList(it)
//        })
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

    companion object {
        @JvmStatic
        fun newInstance() {}
    }
}
