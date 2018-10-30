package com.google.example

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        val navController: NavController = findNavController(R.id.my_nav_host_fragment)
//        navigationView.setupWithNavController(navController)

        setupNavigation()
        setupLocation()
    }



    override fun onSupportNavigateUp(): Boolean {
        return navigateUp(drawerLayout, findNavController(R.id.my_nav_host_fragment))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupLocation() {
        myLocationListener = MyLocationListener(this) {
            location ->
            // Update UI
        }
        lifecycle.addObserver(myLocationListener)
    }

    private fun setupNavigation() {
        val navController: NavController = findNavController(R.id.my_nav_host_fragment)

        // Update action bar to reflect navigation
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // Handle nav drawer item clicks
        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        // Tie nav graph to items in nav drawer
        NavigationUI.setupWithNavController(nav_view, navController)
    }
}


internal class MyLocationListener(
        private val context: Context,
        private val callback: (Location) -> Unit
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        // connect to system location service
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect from system location service
    }
}