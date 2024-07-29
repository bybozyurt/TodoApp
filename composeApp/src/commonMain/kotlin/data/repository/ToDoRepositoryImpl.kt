package data.repository

import data.local.ToDoDao
import domain.RequestState
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl : ToDoRepository {

    lateinit var toDoDao: ToDoDao

    override fun initDao(dao: ToDoDao) {
        toDoDao = dao
    }

    override fun getAllTasks(): Flow<RequestState<List<ToDoTaskEntity>>> =
        toDoDao.getAllTasks().map { tasks ->
            RequestState.Success(tasks.map { it })
        }

    override suspend fun addTask(task: ToDoTaskEntity) {
        toDoDao.addTask(task)
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        toDoDao.updateTask(task)
    }

    override suspend fun deleteTask(task: ToDoTaskEntity) {
        toDoDao.deleteTask(task)
    }
}