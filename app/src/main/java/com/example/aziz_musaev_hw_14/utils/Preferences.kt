package com.example.aziz_musaev_hw_14.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(private var context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("prefences",MODE_PRIVATE)

    fun isBoardingShowed():Boolean{
        return sharedPref.getBoolean("board",false)
    }
    fun setBoardingShowed(isShow: Boolean){
sharedPref.edit().putBoolean("board",isShow).apply()
    }
}