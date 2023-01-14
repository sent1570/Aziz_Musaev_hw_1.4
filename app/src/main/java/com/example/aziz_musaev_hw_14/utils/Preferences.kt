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
    fun getUserName(): String? {
        return sharedPref.getString(GET_USER_NAME,"")
    }

    fun setUserName(string: String){
        sharedPref.edit().putString(GET_USER_NAME, string).apply()
    }
    fun getEmail(): String? {
        return sharedPref.getString(GET_EMAIL,"")
    }

    fun setEmail(string: String){
        sharedPref.edit().putString(GET_EMAIL, string).apply()
    }
    fun getPhone(): String? {
        return sharedPref.getString(GET_PHONE,"")
    }

    fun setPhone(string: String){
        sharedPref.edit().putString(GET_PHONE, string).apply()
    }
    fun getGender(): String? {
        return sharedPref.getString(GET_GENDER,"")
    }

    fun setGender(string: String){
        sharedPref.edit().putString(GET_GENDER, string).apply()
    }
    fun getDateOfBirth(): String? {
        return sharedPref.getString(GET_DATE_OF_BIRTH,"")
    }

    fun setDateOfBirth(string: String){
        sharedPref.edit().putString(GET_DATE_OF_BIRTH, string).apply()
    }
    companion object{
        const val BOARDING_SHOW = "board"
        const val IMAGE = "img"
        const val GET_USER_NAME = "User name"
        const val GET_EMAIL = "Email"
        const val GET_PHONE = "Phone"
        const val GET_GENDER = "Gender"
        const val GET_DATE_OF_BIRTH = "Date of birth"

    }
}