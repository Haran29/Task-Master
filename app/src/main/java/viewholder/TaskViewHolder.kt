package viewholder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.R
import model.TaskEntity

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val deadline: TextView = itemView.findViewById(R.id.deadline)
    val priority: TextView = itemView.findViewById(R.id.priority)

    fun bind(task: TaskEntity) {
        name.text = task.name
        deadline.text = task.description
        priority.text = task.priority.toString()
    }
}
