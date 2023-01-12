package com.example.aziz_musaev_hw_14.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null,
    var imgUri: String,
    var title:String,
    var description:String



):java.io.Serializable
