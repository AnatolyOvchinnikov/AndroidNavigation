package com.example.bottomappbar

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.example.R
import com.google.example.ufc.ui.sample.SampleActivity
import kotlinx.android.synthetic.main.fragment_bottomsheet.*

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
//        val navController: NavController = findNavController()
//
//        // Update action bar to reflect navigation
//        NavigationUI.setupActionBarWithNavController(activity as AppCompatActivity, navController)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            // Bottom Navigation Drawer menu item clicks
            when (menuItem.itemId) {
                R.id.sort_by_name -> {
                    (activity as SampleActivity).sort(SORT_BY_NAME)
                }
                R.id.sort_by_age -> {
                    (activity as SampleActivity).sort(SORT_BY_AGE)
                }
            }
            dismiss()
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }

        // Tie nav graph to items in nav drawerMyLocationListener
//        NavigationUI.setupWithNavController(navigation_view, navController)
    }

    // This is an extension method for easy Toast call
    fun Context.toast(message: CharSequence) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM, 0, 600)
        toast.show()
    }

    companion object {
        const val SORT_BY_NAME = "name"
        const val SORT_BY_AGE = "age"
    }

}