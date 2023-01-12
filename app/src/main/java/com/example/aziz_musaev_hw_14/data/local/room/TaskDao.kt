package com.example.aziz_musaev_hw_14.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aziz_musaev_hw_14.ui.home.TaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(task:TaskModel)

    @Query("SELECT * FROM TaskModel ")
    fun getAllTasks(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY id DESC ")
    fun getListByDate(): List<TaskModel>

    @Query
        ("Select * FROM TaskModel ORDER BY title ASC")
fun getListByAlphabet(): List<TaskModel>

@Delete
fun deleteTask(task: TaskModel)
@Update
fun updateTask(taskModel: TaskModel)
}