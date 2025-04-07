package com.example.taskmanagerapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,           // ID will auto-increment
    val title: String,         // Required title
    val description: String,   // Optional description
    val dueDate: String        // Required due date (could be in yyyy-MM-dd format)
)
