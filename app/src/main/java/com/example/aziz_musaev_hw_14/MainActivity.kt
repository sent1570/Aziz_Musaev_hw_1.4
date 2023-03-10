package com.example.aziz_musaev_hw_14

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aziz_musaev_hw_14.databinding.ActivityMainBinding
import com.example.aziz_musaev_hw_14.services.FirebaseNotifications
import com.example.aziz_musaev_hw_14.utils.Preferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            Log.e("ololo", "onCreate: "+ it.result )
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.newTaskFragment
            )
        )
        if (!Preferences(applicationContext).isBoardingShowed()){
                    navController.navigate(R.id.onBoardFragment)
                }
        else if (auth.currentUser == null){
            navController.navigate(R.id.authFragment)
            navView.visibility = View.GONE
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{

                _, destination, _ ->
            if (destination.id == R.id.navigation_home && auth.currentUser == null && Preferences(applicationContext).isBoardingShowed()){
                navController.navigate(R.id.authFragment)
            }

            if (destination.id == R.id.onBoardFragment && Preferences(applicationContext).isBoardingShowed()){
                navController.navigate(R.id.authFragment)
            }


            if (destination.id == R.id.newTaskFragment|| destination.id == R.id.onBoardFragment|| destination.id == R.id.authFragment){
                navView.visibility = View.GONE
            }else navView.visibility = View.VISIBLE
if (destination.id == R.id.navigation_home && auth.currentUser == null){
    navView.visibility = View.GONE
}


            if(destination.id == R.id.onBoardFragment || destination.id == R.id.authFragment){
    supportActionBar?.hide()
}
            if (destination.id == R.id.navigation_home && auth.currentUser != null){
                supportActionBar?.show()
            }

        }
    }
}