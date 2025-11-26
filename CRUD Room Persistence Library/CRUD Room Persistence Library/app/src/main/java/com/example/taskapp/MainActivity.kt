// MainActivity.kt
package com.example.taskapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.data.TaskEntity
import com.example.taskapp.enums.TaskFilter
import com.example.taskapp.viewmodel.TaskViewModel
import com.example.taskapp.viewmodel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etTask: EditText = findViewById(R.id.etTaskTitle)
        val btnAdd: Button = findViewById(R.id.btnAddTask)
        val recycler: RecyclerView = findViewById(R.id.rvTasks)
        val adapter = TaskAdapter(
            onChecked = { viewModel.update(it) },
            onDelete = { viewModel.delete(it) }
        )
        recycler.adapter = adapter
        viewModel.allTasks.observe(this) {
            adapter.submitList(it)
        }
        btnAdd.setOnClickListener {
            val text = etTask.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.insert(text)
                etTask.setText("")
            }
        }
    }
}