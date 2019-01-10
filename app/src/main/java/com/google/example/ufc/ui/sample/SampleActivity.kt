package com.google.example.ufc.ui.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.navigation.findNavController
import com.example.bottomappbar.BottomNavigationDrawerFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.example.R
import com.google.example.ufc.ui.fighter.FightersListFragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomappbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.filter -> {

            }
//            R.id.app_bar_search -> toast(getString(R.string.search_clicked))
//            R.id.app_bar_settings -> toast(getString(R.string.settings_clicked))
            android.R.id.home -> {
                val bottomNavDrawerFragment = BottomNavigationDrawerFragment()
                bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            }
        }
        return true
    }

    fun sort(sortType: String) {
        val fightersListFragment: FightersListFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment)
                ?.getChildFragmentManager()
                ?.getFragments()
                ?.get(0) as FightersListFragment;
        fightersListFragment.sort(sortType)
    }

    fun changeFab() {
        // Hide navigation drawer icon
        bottom_app_bar.navigationIcon = null

        // Move FAB from the center of BottomAppBar to the end of it
        bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

//        // Replace the action menu
//        bottom_app_bar.replaceMenu(bottomappbar_menu_secondary)

//        // Change FAB icon
//        fab?.setImageDrawable(baseline_reply_white_24)

        fab.setOnClickListener {
            findNavController(R.id.my_nav_host_fragment).popBackStack()

            val icon = DrawerArrowDrawable(this)
            bottom_app_bar.navigationIcon = icon

            bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

            fab.setOnClickListener(null)
        }
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