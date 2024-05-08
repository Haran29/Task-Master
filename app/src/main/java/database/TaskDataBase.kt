package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {


    companion object {
        @Volatile
        private var INSTANCE: TaskDataBase? = null

        fun getInstance(context: Context): TaskDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    "tasks_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
