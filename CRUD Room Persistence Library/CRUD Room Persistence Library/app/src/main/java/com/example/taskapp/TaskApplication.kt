package com.example.taskapp

import android.app.Application
import com.example.taskapp.data.TaskDatabase
import com.example.taskapp.repository.TaskRepository

class TaskApplication : Application() {
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}
