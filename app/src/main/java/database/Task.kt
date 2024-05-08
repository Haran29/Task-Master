package database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(

    val name: String?,
    val description: String?,
    val priority: Int?,
    val deadline: Long?


){
    @PrimaryKey(autoGenerate = true)
    val id: Long?= null
}
