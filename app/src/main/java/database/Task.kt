package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var name: String,
    var description: String?,
    var priority: Int,
    var isChecked: Boolean = false
)
{
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
}