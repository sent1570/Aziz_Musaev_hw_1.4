package com.example.aziz_musaev_hw_14.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("prefences",MODE_PRIVATE)

    fun isBoardingShowed() : Boolean {
        return sharedPref.getBoolean(BOARDING_SHOW , false)
    }
    fun setBoardingShowed(isShow: Boolean){
        sharedPref.edit().putBoolean(BOARDING_SHOW, isShow).apply()
    }
    fun getImageUri(): String{
        return sharedPref.getString(IMAGE,"").toString()
    }
    fun saveImageUri(imgUri:String){
        sharedPref.edit().putString(IMAGE,imgUri).apply()
    }
    fun getString(): String? {
        return sharedPref.getString(GET_STRING,"")
    }

    fun setTextShowed(string: String){
        sharedPref.edit().putString(GET_STRING, string).apply()
    }
    companion object{
        const val BOARDING_SHOW = "board"
        const val IMAGE = "img"
        const val GET_STRING = "string"
    }
}