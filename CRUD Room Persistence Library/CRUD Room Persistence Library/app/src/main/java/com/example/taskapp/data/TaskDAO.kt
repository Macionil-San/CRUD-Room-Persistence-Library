// data/TaskDao.kt
package com.example.taskapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)
    @Query("SELECT * FROM task_table ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>
    @Update
    suspend fun update(task: TaskEntity)
    @Delete
    suspend fun delete(task: TaskEntity)
    @Query("DELETE FROM task_table")
    suspend fun deleteAll()
}