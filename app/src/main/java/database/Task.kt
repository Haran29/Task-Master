package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    val name: String,
    val description: String?,
    val priority: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id :Int? = null
}