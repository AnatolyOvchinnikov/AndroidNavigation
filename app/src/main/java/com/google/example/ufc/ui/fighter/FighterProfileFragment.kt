package com.google.example.ufc.ui.fighter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.example.R
import com.google.example.databinding.FragmentFighterDetailsBinding
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.db.FightersLocalCache
import kotlinx.android.synthetic.main.fragment_fighter_details.*
import java.util.concurrent.Executors

class FighterProfileFragment : Fragment() {

    private val viewModel by lazy {
        val cache = FightersLocalCache(MainDatabase.getInstance(requireContext()).fightersDao(), Executors.newSingleThreadExecutor())
        val repository = FightersRepository(Api.create(), cache)
        ViewModelProviders.of(this, FighterProfileViewModel.ViewModelFactory(repository)).get(FighterProfileViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding: FragmentFighterDetailsBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_fighter_details, container, false)
        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = FighterProfileFragmentArgs.fromBundle(arguments).id
        viewModel.loadFighterProfile(id)
        viewModel.fighterData.observe(this, Observer {
            Glide.with(this).load(it.avatar).into(profileAvatar);
        })
    }
}
