package com.google.example.ufc.ui.sample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import com.example.bottomappbar.BottomNavigationDrawerFragment
import com.google.example.R
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        setSupportActionBar(bottom_app_bar)

        val icon = DrawerArrowDrawable(this)
        bottom_app_bar.navigationIcon = icon

        setupNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
//            R.id.app_bar_fav -> toast(getString(R.string.fav_clicked))
//            R.id.app_bar_search -> toast(getString(R.string.search_clicked))
//            R.id.app_bar_settings -> toast(getString(R.string.settings_clicked))
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

    private fun setupNavigation() {
//        val navController: NavController = findNavController(R.id.my_nav_host_fragment)

        // Update action bar to reflect navigation
//        NavigationUI.setupActionBarWithNavController(this, navController)

//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // Handle nav drawer item clicks
//        nav_view.setNavigationItemSelectedListener { menuItem ->
//            menuItem.isChecked = true
//            drawerLayout.closeDrawers()
//            true
//        }
//
//        // Tie nav graph to items in nav drawerMyLocationListener
//        NavigationUI.setupWithNavController(nav_view, navController)
    }
}