package com.example.aziz_musaev_hw_14

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aziz_musaev_hw_14.databinding.ActivityMainBinding
import com.example.aziz_musaev_hw_14.utils.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.newTaskFragment
            )
        )

        if (Preferences(applicationContext).isBoardingShowed()){
            navController.navigate(R.id.navigation_home)
        } else{
            navController.navigate(R.id.onBoardFragment)
        }

        navController.navigate(R.id.onBoardFragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{
                _, destination, _ ->
            if (destination.id == R.id.newTaskFragment|| destination.id == R.id.onBoardFragment ){
                navView.visibility = View.GONE
            }else navView.visibility = View.VISIBLE

            if (destination.id == R.id.onBoardFragment){
                supportActionBar?.hide()
            }
        }
    }
}