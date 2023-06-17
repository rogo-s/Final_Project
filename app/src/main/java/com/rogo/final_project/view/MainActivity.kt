package com.rogo.final_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rogo.final_project.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragContainer) as NavHostFragment

        navController = navHostFragment.navController
        bottomNav = findViewById(R.id.bottomNav)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment2,
            R.id.historyNonLoginFragment2,
            R.id.notifFragment2,
            R.id.akunNonLoginFragment2
        ).build()

        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_,item,_ ->
            when(item.id){
                R.id.homeFragment2 -> hideBottom(false)
                R.id.historyNonLoginFragment2 -> hideBottom(false)
                R.id.notifFragment2 -> hideBottom(false)
                R.id.akunNonLoginFragment2 -> hideBottom(false)
                else -> hideBottom(true)
            }
        }
    }

    private fun hideBottom(hide: Boolean) {
        if (hide){
            bottomNav.visibility = View.GONE
        }else{
            bottomNav.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}