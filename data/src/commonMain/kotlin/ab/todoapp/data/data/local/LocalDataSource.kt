package ab.todoapp.data.data.local

import ab.todoapp.data.data.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllTasks(): Flow<List<ToDoTaskEntity>>

    suspend fun addTask(task: ToDoTaskEntity)

    suspend fun updateTask(task: ToDoTaskEntity)

    suspend fun deleteTask(id: Long)

    suspend fun getTaskById(id: Long): ToDoTaskEntity?
}