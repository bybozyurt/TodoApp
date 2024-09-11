package data.local

import database.ToDoDatabase
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSourceImpl(
    private val db: ToDoDatabase,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
) : LocalDataSource {

    override fun getAllTasks(): Flow<List<ToDoTaskEntity>> {
        return db.toDoDao().getAllTasks()
    }

    override suspend fun addTask(task: ToDoTaskEntity) {
        externalScope.launch(dispatcher) {
            db.toDoDao().addTask(task)
        }
    }

    override suspend fun updateTask(task: ToDoTaskEntity) {
        externalScope.launch(dispatcher) {
            db.toDoDao().updateTask(task)
        }
    }

    override suspend fun deleteTask(task: ToDoTaskEntity) {
        externalScope.launch(dispatcher) {
            db.toDoDao().deleteTask(task)
        }
    }

    override suspend fun getTaskById(id: Long): ToDoTaskEntity? {
        return db.toDoDao().getTaskById(id)
    }
}