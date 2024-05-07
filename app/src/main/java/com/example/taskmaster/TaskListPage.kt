package com.example.taskmaster

import Adapter.TaskAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.TaskEntity

class TaskListPage : AppCompatActivity() {
    val taskList = listOf(
        TaskEntity(
            name = "Complete project proposal",
            description = "Finish writing the project proposal and send it to the supervisor",
            priority = 1,
            deadline = System.currentTimeMillis() + (24 * 60 * 60 * 1000) // Deadline set to 24 hours from current time
        ),
        TaskEntity(
            name = "Buy groceries",
            description = "Buy fruits, vegetables, milk, and bread from the grocery store",
            priority = 2,
            deadline = System.currentTimeMillis() + (3 * 24 * 60 * 60 * 1000) // Deadline set to 3 days from current time
        ),
        TaskEntity(
            name = "Prepare for exam",
            description = "Study for the upcoming exam on calculus",
            priority = 3,
            deadline = System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000) // Deadline set to 7 days from current time
        ),
        TaskEntity(
            name = "Call mom",
            description = "Call mom to wish her happy birthday",
            priority = 2,
            deadline = System.currentTimeMillis() + (2 * 24 * 60 * 60 * 1000) // Deadline set to 2 days from current time
        ),
        TaskEntity(
            name = "Go for a run",
            description = "Go for a 5 km run in the park",
            priority = 1,
            deadline = System.currentTimeMillis() + (12 * 60 * 60 * 1000) // Deadline set to 12 hours from current time
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list_page)

        var rvList:RecyclerView = findViewById(R.id.rvList)
        rvList.layoutManager = LinearLayoutManager(this)


        val adapter = TaskAdapter(taskList)
        rvList.adapter = adapter

    }
}