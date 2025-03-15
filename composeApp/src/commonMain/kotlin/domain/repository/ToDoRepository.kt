package domain.repository

import data.model.ToDoTaskEntity
import domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getAllTasks(): Flow<List<ToDoTask>>

    suspend fun addTask(task: ToDoTask)

    suspend fun updateTask(task: ToDoTask)

    suspend fun deleteTask(id: Long)

    suspend fun getTaskById(id: Long): ToDoTask?

}