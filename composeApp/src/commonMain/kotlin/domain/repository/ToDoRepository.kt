package domain.repository

import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getAllTasks(): Flow<List<ToDoTaskEntity>>

    suspend fun addTask(task: ToDoTaskEntity)

    suspend fun updateTask(task: ToDoTaskEntity)

    suspend fun deleteTask(id: Long)

    suspend fun getTaskById(id: Long): ToDoTaskEntity?

}