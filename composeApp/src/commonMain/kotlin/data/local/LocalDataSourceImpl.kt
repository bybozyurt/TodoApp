package data.local

import database.ToDoDatabase
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val db: ToDoDatabase,
    private val dispatcher: CoroutineDispatcher,
) : LocalDataSource {

    override fun getAllTasks(): Flow<List<ToDoTaskEntity>> {
        return db.toDoDao().getAllTasks()
    }

    override suspend fun addTask(task: ToDoTaskEntity) {
        withContext(dispatcher) {
            db.toDoDao().addTask(task)
        }
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        withContext(dispatcher) {
            db.toDoDao().updateTask(task)
        }
    }

    override suspend fun deleteTask(id: Long) {
        withContext(dispatcher) {
            db.toDoDao().getTaskById(id)?.let {
                db.toDoDao().deleteTask(it)
            }
        }
    }

    override suspend fun getTaskById(id: Long): ToDoTaskEntity? {
        return withContext(dispatcher) {
            db.toDoDao().getTaskById(id)
        }
    }
}