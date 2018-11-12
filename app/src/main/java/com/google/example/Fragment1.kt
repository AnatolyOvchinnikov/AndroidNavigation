package com.google.example

import android.arch.lifecycle.*
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment1_layout.*

class Fragment1 : Fragment() {

    private lateinit var mModel: NameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        initLiveData()
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

        var counter = 0
        todoSomething.setOnClickListener {
            counter++
            mModel.currentName.value = "Test ${counter}"
        }
    }

    private fun initLiveData() {
        mModel = ViewModelProviders.of(this, NameViewModel.NameViewFactory("Test")).get(NameViewModel::class.java)
        mModel.currentName.observe(this, Observer<String> { newName ->
            newName?.let {
                mModel.checkArg()
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })


        val intLiveData: LiveData<Int> = Transformations.map(mModel.currentName, {
            val a: String = Regex("[^0-9]").replace(it, "")
            Integer.parseInt(a)
        })
        intLiveData.observe(this, Observer {
            Log.v(javaClass.name, "Int: ${it}")
        })

        val userLiveData: LiveData<User> = Transformations.map(mModel.currentName, {
            getUser(it)
        })
        userLiveData.observe(this, Observer {
            Log.v(javaClass.name, it?.name)
        })

        val liveDataUid: LiveData<String>? = Transformations.switchMap(userLiveData, {
            val userUid = MutableLiveData<String>()
            userUid.value = it.uid
            userUid
        })

        liveDataUid?.observe(this, Observer {
            Log.v(javaClass.name, it.toString())
        })
    }

    private fun getUser(name: String): User {
        return User(name)
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
