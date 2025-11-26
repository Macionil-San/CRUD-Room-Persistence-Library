// viewmodel/TaskViewModel.kt
package com.example.taskapp.viewmodel

import androidx.lifecycle.*

import com.example.taskapp.data.TaskEntity
import com.example.taskapp.enums.TaskFilter
import com.example.taskapp.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) :
    ViewModel() {
    val allTasks = repository.allTasks.asLiveData()
    fun insert(title: String) = viewModelScope.launch {
        repository.insert(TaskEntity(title = title))
    }
    fun update(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }
    fun delete(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
    }
}
class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
