package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class AddEditTaskActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()
    private var taskId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        val etTitle = findViewById<EditText>(R.id.etTaskTitle)
        val etDescription = findViewById<EditText>(R.id.etTaskDescription)
        val etDueDate = findViewById<EditText>(R.id.etDueDate)
        val btnSave = findViewById<Button>(R.id.btnSaveTask)

        taskId = intent.getIntExtra("task_id", -1).takeIf { it != -1 }

        // If taskId exists, load task and prefill fields
        taskId?.let { id ->
            taskViewModel.getTaskById(id).observe(this, Observer { task ->
                task?.let {
                    etTitle.setText(task.title)
                    etDescription.setText(task.description)
                    etDueDate.setText(task.dueDate)
                }
            })
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val dueDate = etDueDate.text.toString()

            if (title.isNotEmpty() && dueDate.isNotEmpty()) {
                if (taskId != null) {
                    // Update task
                    val updatedTask = Task(id = taskId!!, title = title, description = description, dueDate = dueDate)
                    taskViewModel.update(updatedTask)
                } else {
                    // New task
                    val newTask = Task(title = title, description = description, dueDate = dueDate)
                    taskViewModel.insert(newTask)
                }
                finish()
            }
        }
    }
}