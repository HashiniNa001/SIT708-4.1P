package com.example.taskmanagerapp

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    // LiveData list of tasks to observe in the UI
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    // Insert a new task into the database
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    // Update an existing task in the database
    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    // Delete a task from the database
    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    fun getTaskById(id: Int): LiveData<Task> = taskDao.getTaskById(id)

}

