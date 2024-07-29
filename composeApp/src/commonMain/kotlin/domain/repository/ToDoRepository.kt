package domain.repository

import data.local.ToDoDao
import domain.RequestState
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun initDao(dao: ToDoDao)

    fun getAllTasks(): Flow<RequestState<List<ToDoTaskEntity>>>

    suspend fun addTask(task: ToDoTaskEntity)

    suspend fun updateTask(task: ToDoTaskEntity)

    suspend fun deleteTask(task: ToDoTaskEntity)

}