package com.example.aziz_musaev_hw_14.extenssion

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun View.loadImage(url:String){
    Glide.with(this).load(url).circleCrop().into(this as ImageView)
}
fun Fragment.showToast(msg:String){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}