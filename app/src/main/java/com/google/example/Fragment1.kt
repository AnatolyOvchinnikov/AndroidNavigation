package com.google.example

import android.annotation.SuppressLint
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
import io.reactivex.Flowable
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
            mModel.liveData1.value = "liveData 1-${counter}"
            mModel.liveData2.value = "liveData 2-${counter}"
        }

        addUser.setOnClickListener {
            mModel.insertUser(userName.text.toString())
        }

        allUsers.setOnClickListener {
            mModel.selectUsers()
//            for(i in 1..100) {
//                mModel.testQuery()
//            }
        }

        try {
            getUser()
        } catch (e: Exception) {

        }
    }

    @SuppressLint("CheckResult")
    private fun getUser() {
        mModel.getUser(1)
                .subscribe({
                    it.getComments()?.subscribe({
                        val res = it
                    }, {
                        val err = it
                    })
                    val result = it
                }, {
                    val error = it
                })
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
            if(it == 2) {
                intLiveData.removeObservers(this)
            }
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

        mModel.mediatorLiveData.observe(this, Observer {
            Log.v(javaClass.name, it.toString())
        })

        val flowable: Flowable<String> = Flowable.just("test 1", "test 2", "test 3")
        val liveDataFromFlowable: LiveData<String> = LiveDataReactiveStreams.fromPublisher(flowable)
        liveDataFromFlowable.observe(this, Observer{
            val arg = it
        })



        Flowable.generate<String> {



            it.onNext("t1")
            it.onNext("t2")
            it.onNext("t3")
        }
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
