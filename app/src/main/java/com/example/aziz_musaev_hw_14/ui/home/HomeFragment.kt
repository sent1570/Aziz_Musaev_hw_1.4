package com.example.aziz_musaev_hw_14.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aziz_musaev_hw_14.App
import com.example.aziz_musaev_hw_14.R
import com.example.aziz_musaev_hw_14.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var taskAdapter: TaskAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initViews();
        setData()
        initListener();
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onClick, context = requireContext(), this::onLongClick)
    }

    private fun onClick(pos: Int) {
        val task = taskAdapter.getTask(pos)
        findNavController().navigate(R.id.newTaskFragment, bundleOf("edit" to task))
    }

    private fun initListener() {
        binding.fadHome.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }

    }

    private fun onLongClick(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да") { _, _ ->
                App.db.dao().deleteTask(taskAdapter.getTask(pos))
                setData()

            }
            setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort) {
            val items = arrayOf("Дата", "По алфавиту")
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setTitle("Сортировать по:")
                setItems(items) { dialog, which ->
                    when (which) {
                        0 -> {
                            taskAdapter.addTasks(App.db.dao().getListByDate())
                        }
                        1 -> {
                            taskAdapter.addTasks(App.db.dao().getListByAlphabet())
                        }
                    }
                }

                show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initViews() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

    }

    private fun setData() {
        val listOfTask = App.db.dao().getAllTasks()
        taskAdapter.addTasks(listOfTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}