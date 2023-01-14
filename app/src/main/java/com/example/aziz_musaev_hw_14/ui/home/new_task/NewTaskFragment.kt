package com.example.aziz_musaev_hw_14.ui.home.new_task

import android.net.Uri
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.aziz_musaev_hw_14.App
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.FragmentNewTaskBinding
import com.example.aziz_musaev_hw_14.extenssion.loadImage
import com.example.aziz_musaev_hw_14.ui.home.TaskModel


class NewTaskFragment : Fragment() {
    var imgUri: String = ""
    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var task: TaskModel

    private var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
binding.imgNewTask.loadImage(uri.toString())
            imgUri = uri.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)
        binding.imgNewTask.loadImage(imgUri)
        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {

            if (arguments != null) {
                updateData(task)
            } else {
                saveData()
            }
            findNavController().navigateUp()
        }
        binding.imgNewTask.setOnClickListener {
            mGetContent.launch("image/*");
        }
    }

    private fun saveData() {
        App.db.dao().insert(
            TaskModel(
                imgUri = imgUri,
                title = binding.etTitle.text.toString(),
                description = binding.etDesc.text.toString(),
            ))
    }

    private fun updateData(taskModel: TaskModel) {
        taskModel.title = binding.etTitle.text.toString()
        taskModel.description = binding.etDesc.text.toString()
        taskModel.imgUri = imgUri
        App.db.dao().updateTask(taskModel)

    }

    private fun initViews() {
        if (arguments != null) {
            binding.btnSave.text = "Update"
            task = arguments?.getSerializable("edit") as TaskModel


            binding.imgNewTask.loadImage(task.imgUri)
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.description)
        } else {
            binding.btnSave.text = "Save"
        }

    }


}