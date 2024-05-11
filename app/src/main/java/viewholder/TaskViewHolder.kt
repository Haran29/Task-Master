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
    private val checkBox:CheckBox = itemView.findViewById(R.id.checkbox)
    val name: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.description)


    fun bind(task: Task) {
        name.text = task.name
        description.text = task.description.toString()
        checkBox.isChecked = task.isChecked

    }
}