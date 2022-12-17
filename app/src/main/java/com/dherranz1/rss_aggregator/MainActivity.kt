package com.dherranz1.rss_aggregator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.dherranz1.rss_aggregator.presentation.RssFeedFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation(){
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNav.setOnItemSelectedListener { menuItem ->
            val navController = findNavController(R.id.nav_host_fragment)

            when(menuItem.itemId){
                R.id.bottom_nav_menu_item_rss -> navController.navigate(NavGraphDirections.actionToRssFeedFragment())
                R.id.bottom_nav_menu_item_management -> navController.navigate(NavGraphDirections.actionToRssManagementFragment())
                R.id.bottom_nav_menu_item_profile -> navController.navigate(NavGraphDirections.actionToProfileFragment())
            }
            true
        }
    }
}