package data.local

import database.ToDoDatabase
import domain.RequestState
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val db: ToDoDatabase,
): LocalDataSource {

    override fun getAllTasks(): Flow<RequestState<List<ToDoTaskEntity>>> {
        return db.toDoDao().getAllTasks().map {
            tasks -> RequestState.Success(tasks.map { it })
        }
    }

    override suspend fun addTask(task: ToDoTaskEntity) {
       db.toDoDao().addTask(task)
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        db.toDoDao().updateTask(task)
    }

    override suspend fun deleteTask(task: ToDoTaskEntity) {
        db.toDoDao().deleteTask(task)
    }
}