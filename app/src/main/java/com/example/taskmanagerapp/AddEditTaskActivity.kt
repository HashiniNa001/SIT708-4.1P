package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddEditTaskActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_task)

        val etTitle = findViewById<EditText>(R.id.etTaskTitle)
        val etDescription = findViewById<EditText>(R.id.etTaskDescription)
        val etDueDate = findViewById<EditText>(R.id.etDueDate)
        val btnSave = findViewById<Button>(R.id.btnSaveTask)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val dueDate = etDueDate.text.toString()

            if (title.isNotEmpty() && dueDate.isNotEmpty()) {
                val task = Task(title = title, description = description, dueDate = dueDate)
                taskViewModel.insert(task)
                finish() // Go back to MainActivity
            }
        }
    }
}
