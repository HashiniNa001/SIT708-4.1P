package com.example.taskmanagerapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmanagerapp.Task

@Dao
interface TaskDao {

    // Insert a new task
    @Insert
    suspend fun insert(task: Task)

    // Update an existing task
    @Update
    suspend fun update(task: Task)

    // Delete a task
    @Delete
    suspend fun delete(task: Task)

    // Get all tasks, sorted by due date
    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    fun getAllTasks(): LiveData<List<Task>>
}
