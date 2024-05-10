package viewholder

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmaster.R
import database.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivDelete: ImageView = itemView.findViewById(R.id.delete_button)
    val checkBox:CheckBox = itemView.findViewById(R.id.checkbox)
    val name: TextView = itemView.findViewById(R.id.name)
    private val deadline: TextView = itemView.findViewById(R.id.description)
    private val priority: TextView = itemView.findViewById(R.id.priority)

    fun bind(task: Task) {
        name.text = task.name
        deadline.text = task.description.toString()
        priority.text = task.priority.toString()
    }
}