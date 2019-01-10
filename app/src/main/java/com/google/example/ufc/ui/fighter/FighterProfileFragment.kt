package com.google.example.ufc.ui.fighter


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.android.codelabs.paging.db.MainDatabase
import com.google.codelabs.mdc.kotlin.shrine.BackdropIconClickListener
import com.google.example.R
import com.google.example.ufc.api.Api
import com.google.example.ufc.data.FightersRepository
import com.google.example.ufc.db.FightersLocalCache
import kotlinx.android.synthetic.main.fragment_fighter_details.*
import java.util.concurrent.Executors

class FighterProfileFragment : Fragment() {

    private lateinit var viewModel: FighterProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighter_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            showBackdrop.setOnClickListener(BackdropIconClickListener(
                    it,
                    fighter_details,
                    AccelerateDecelerateInterpolator()))
        }

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fighter_details.background = context?.getDrawable(R.drawable.fighter_details_background_shape)
        }

        val cache = FightersLocalCache(MainDatabase.getInstance(requireContext()).fightersDao(), Executors.newSingleThreadExecutor())
        val repository = FightersRepository(Api.create(), cache)

        viewModel = ViewModelProviders.of(this, FighterProfileViewModel.ViewModelFactory(repository)).get(FighterProfileViewModel::class.java)

        val id = FighterProfileFragmentArgs.fromBundle(arguments).id
        viewModel.loadFighterProfile(id)
        viewModel.getFighterProfile(id).observe(this, Observer { it ->
            it?.let { it1 ->
                Glide.with(this).load(it1.avatar).into(profileAvatar);
                profileName.setText(it1.name)
                profileAge.setText(it1.age.toString())
            }
        })
    }
}
