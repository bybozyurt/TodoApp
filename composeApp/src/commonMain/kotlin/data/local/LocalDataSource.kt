package data.local

import domain.RequestState
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllTasks(): Flow<RequestState<List<ToDoTaskEntity>>>

    suspend fun addTask(task: ToDoTaskEntity)

    suspend fun updateTask(task: ToDoTaskEntity)

    suspend fun deleteTask(task: ToDoTaskEntity)
}