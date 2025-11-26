// repository/TaskRepository.kt
package com.example.taskapp.repository


import com.example.taskapp.data.TaskDao
import com.example.taskapp.data.TaskEntity
import com.example.taskapp.enums.TaskFilter
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()
    suspend fun insert(task: TaskEntity) {
        dao.insert(task)
    }
    suspend fun update(task: TaskEntity) {
        dao.update(task)
    }
    suspend fun delete(task: TaskEntity) {
        dao.delete(task)
    }
}