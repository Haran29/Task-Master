package database

import androidx.lifecycle.LiveData

class TaskRepository(private val db: TaskDatabase) {

    suspend fun insertTask(task: Task) = db.getTaskDao().insertTask(task)
    suspend fun deleteTask(task: Task) = db.getTaskDao().deleteTask(task)

    suspend fun updateTask(task: Task) = db.getTaskDao().updateTask(task)

    suspend fun getAllTask(task: Task) = db.getTaskDao().getAllTasks()



}
