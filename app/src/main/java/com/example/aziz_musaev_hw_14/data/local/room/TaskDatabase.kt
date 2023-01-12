package com.example.aziz_musaev_hw_14.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aziz_musaev_hw_14.ui.home.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun dao():TaskDao
}