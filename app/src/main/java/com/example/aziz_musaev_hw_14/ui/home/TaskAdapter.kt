package com.example.aziz_musaev_hw_14.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.aziz_musaev_hw_14.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>(){

private var taskList = arrayListOf<TaskModel>()

    fun addTask(taskModel: TaskModel){
        taskList.add(0,taskModel)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size
    inner class ViewHolder(private var binding:TaskItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
            binding.imgItem.setImageURI(taskModel.imgUri.toUri())
        }

    }
}