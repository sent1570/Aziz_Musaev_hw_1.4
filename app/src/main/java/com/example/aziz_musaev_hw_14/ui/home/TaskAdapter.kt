package com.example.aziz_musaev_hw_14.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.TaskItemBinding
import com.example.aziz_musaev_hw_14.extenssion.loadImage

class TaskAdapter(private var onClick:(Int)-> Unit ,
                  val context: Context,
    private var onLongClick: (Int)-> Unit) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private var taskList = arrayListOf<TaskModel>()

    inner class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("ResourceType")
        fun bind(taskModel: TaskModel) {
            if (adapterPosition % 2 == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.viewItem.setBackgroundColor(ContextCompat.getColor(context,R.color.white))

            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.black))
                binding.tvDesc.setTextColor(ContextCompat.getColor(context,R.color.black))
                binding.viewItem.setBackgroundColor(ContextCompat.getColor(context,R.color.black))

            }
            fun updateImage(){
                binding.imgItem.loadImage("")
            }
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
            // binding.imgItem.setImageURI(taskModel.imgUri.toUri())

            binding.imgItem.loadImage(taskModel.imgUri)
            itemView.setOnLongClickListener {
                onLongClick(adapterPosition)
                Toast.makeText(itemView.context, adapterPosition.toString(), Toast.LENGTH_SHORT)
                    .show()
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    fun getTask(pos: Int): TaskModel {
        return taskList[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])

    }

    override fun getItemCount(): Int = taskList.size
}


