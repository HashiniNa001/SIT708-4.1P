package com.example.taskmanagerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.databinding.ItemTaskBinding

class TaskAdapter(private var taskList: List<Task>, private val onClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task, onClick)
    }

    override fun getItemCount(): Int = taskList.size


    fun updateTaskList(newList: List<Task>) {
        taskList = newList
        notifyDataSetChanged()
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, onClick: (Task) -> Unit) {
            binding.taskTitle.text = task.title
            binding.taskDueDate.text = task.dueDate
            binding.root.setOnClickListener { onClick(task) }
        }
    }
}
