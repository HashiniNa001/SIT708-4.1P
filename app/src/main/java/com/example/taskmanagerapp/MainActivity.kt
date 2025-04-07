package com.example.taskmanagerapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TaskAdapter(emptyList()) { task ->
            val intent = Intent(this, AddEditTaskActivity::class.java)
            intent.putExtra("task_id", task.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                adapter.updateTaskList(it)
            }
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddEditTaskActivity::class.java)
            startActivity(intent)
        }
    }
}