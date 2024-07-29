package data.repository

import data.local.LocalDataSource
import domain.RequestState
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : ToDoRepository {

    override fun getAllTasks(): Flow<RequestState<List<ToDoTaskEntity>>>? {
        return null
    }

    override suspend fun addTask(task: ToDoTaskEntity) {
        //localDataSource.addTask(task)
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        //localDataSource.updateTask(task)
    }

    override suspend fun deleteTask(task: ToDoTaskEntity) {
        //localDataSource.deleteTask(task)
    }
}