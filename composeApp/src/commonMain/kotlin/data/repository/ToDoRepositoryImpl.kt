package data.repository

import data.local.LocalDataSource
import data.mapper.toDomain
import data.mapper.toEntity
import domain.model.ToDoTask
import domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : ToDoRepository {

    override fun getAllTasks(): Flow<List<ToDoTask>> {
        return localDataSource.getAllTasks().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addTask(task: ToDoTask) {
        localDataSource.addTask(task.toEntity())
    }

    override suspend fun updateTask(task: ToDoTask) {
        localDataSource.updateTask(task.toEntity())
    }

    override suspend fun deleteTask(id: Long) {
        localDataSource.deleteTask(id)
    }

    override suspend fun getTaskById(id: Long): ToDoTask? {
        return localDataSource.getTaskById(id)?.toDomain()
    }
}