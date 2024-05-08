package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TaskEntityDao {
    @Insert
    suspend fun insertTaskEntity(task:TaskEntity)
    @Delete
    suspend fun deleteTaskEntity(task:TaskEntity)
    @Query("SELECT * FROM tasks")
    fun getAllTaskEntity():List<TaskEntity>

}