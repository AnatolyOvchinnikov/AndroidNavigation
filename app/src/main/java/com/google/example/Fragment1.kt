package com.google.example

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment1_layout.*

class Fragment1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment1_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Fragment1Directions.actionFragment1ToFragment2()
//        Fragment2Args.

//        val bundle = Bundle()
//        bundle.putString("arg1", "From 1 fragment")
//        bundle.putInt("arg2", 777)
//        goToFragment2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fragment1_to_fragment2, bundle))

        goToFragment2.setOnClickListener {
            val directions = Fragment1Directions.actionFragment1ToFragment2("Type-safe method").setArg2(911)
            it.findNavController().navigate(directions)
        }
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
