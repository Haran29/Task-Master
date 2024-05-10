package com.example.taskmaster

import Adapter.TaskAdapter
import TaskPageDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class TaskListPage : AppCompatActivity() {

    private lateinit var repository: TaskRepository
    private lateinit var viewModel: TaskPageDataViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        print(1)
        super.onCreate(savedInstanceState)
        print(2)
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
        // Refresh data from the repository
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTasks()
            // Update LiveData in ViewModel
            viewModel.setData(data)
        }
    }

    private fun displayDialog(repository: TaskRepository) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter New Task item:")
        val view = layoutInflater.inflate(R.layout.dialog_task_input, null)
        builder.setView(view)

        val taskNameInput = view.findViewById<EditText>(R.id.taskNameInput)
        val taskDescriptionInput = view.findViewById<EditText>(R.id.taskDescriptionInput)
        val taskPriorityInput = view.findViewById<EditText>(R.id.taskPriorityInput)
        val taskDeadlineInput = view.findViewById<EditText>(R.id.taskDeadlineInput)

        builder.setPositiveButton("OK") { dialog, which ->
            val name = taskNameInput.text.toString()
            val description = taskDescriptionInput.text.toString()
            val priority = taskPriorityInput.text.toString().toIntOrNull()
            val deadline = taskDeadlineInput.text.toString().toLongOrNull()

            if (name.isNotBlank() && priority != null && deadline != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insertTask(Task(name, description, priority))
                }
            }
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
