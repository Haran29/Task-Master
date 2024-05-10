package Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.R
import database.Task
import database.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskAdapter(
    private var tasks: List<Task>,
    private val repository: TaskRepository
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val priorityTextView: TextView = itemView.findViewById(R.id.priority)
        private val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)

        fun bind(task: Task) {
            nameTextView.text = task.name
            priorityTextView.text = task.priority.toString()

            deleteButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deleteTask(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_task_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = tasks.size

    fun submitList(newList: List<Task>) {
        tasks = newList
        notifyDataSetChanged()
    }
}
