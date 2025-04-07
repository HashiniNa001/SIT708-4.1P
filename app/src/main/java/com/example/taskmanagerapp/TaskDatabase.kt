package com.example.taskmanagerapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Step 1: Add your Entity class and DAO
@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    // Step 2: Abstract method for your DAO
    abstract fun taskDao(): TaskDao

    // Step 3: Singleton pattern to get a single DB instance
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
