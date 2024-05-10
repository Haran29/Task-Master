package com.example.taskmaster

import Adapter.TaskAdapter
import TaskPageDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import database.Task
import database.TaskDatabase
import database.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskListPage : AppCompatActivity() {

    private lateinit var repository: TaskRepository
    private lateinit var viewModel: TaskPageDataViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list_page)

        val taskDao = TaskDatabase.getInstance(this).taskDao()
        repository = TaskRepository(taskDao)
        viewModel = ViewModelProvider(this)[TaskPageDataViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.rvList)
        adapter = TaskAdapter(emptyList(), repository) // Initialize adapter with an empty list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the LiveData from the ViewModel
        viewModel.data.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        // Refresh data when activity starts
        refreshData()

        val addButton: Button = findViewById(R.id.add_btn)
        addButton.setOnClickListener {
            displayDialog(repository)
        }
    }

    private fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = repository.getAllTasks()
                withContext(Dispatchers.Main) {
                    viewModel.setData(data)
                }
            } catch (e: Exception) {
                Log.e("TaskListPage", "Failed to fetch tasks", e)
            }
        }
    }


    private fun displayDialog(repository: TaskRepository) {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_task_input, null)
        builder.setView(view)

        val taskNameInput = view.findViewById<EditText>(R.id.taskNameInput)
        val taskDescriptionInput = view.findViewById<EditText>(R.id.taskDescriptionInput)
        val taskPriorityInput = view.findViewById<EditText>(R.id.taskPriorityInput)

        val alertDialog = builder.create()

        // Handle OK button click
        view.findViewById<Button>(R.id.okButton).setOnClickListener {
            val name = taskNameInput.text.toString()
            val description = taskDescriptionInput.text.toString()
            val priority = try {
                taskPriorityInput.text.toString().toInt()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
                null
            }

            if (name.isNotBlank() && priority != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        repository.insertTask(Task(name, description, priority))
                        alertDialog.dismiss()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        // Handle Close button click
        view.findViewById<Button>(R.id.closeButton).setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }


}
