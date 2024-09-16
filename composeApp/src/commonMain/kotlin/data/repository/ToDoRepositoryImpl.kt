package data.repository

import data.local.LocalDataSource
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : ToDoRepository {

    override fun getAllTasks(): Flow<List<ToDoTaskEntity>> {
        return localDataSource.getAllTasks().map { state ->
            state.sortedByDescending { !it.isCompleted }
        }
    }

    override suspend fun addTask(task: ToDoTaskEntity) {
        localDataSource.addTask(task)
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        localDataSource.updateTask(task)
    }

    override suspend fun deleteTask(task: ToDoTaskEntity) {
        localDataSource.deleteTask(task)
    }

    override suspend fun getTaskById(id: Long): ToDoTaskEntity? {
        return localDataSource.getTaskById(id)
    }
}