package data.local

import androidx.room.InvalidationTracker
import database.ToDoDatabase
import database.dao.ToDoDao
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MockDatabase : ToDoDatabase() {

    private val tasksFlow = MutableStateFlow<List<ToDoTaskEntity>>(emptyList())

    override fun toDoDao(): ToDoDao {
        return object : ToDoDao {
            override fun getAllTasks(): Flow<List<ToDoTaskEntity>> = tasksFlow

            override suspend fun addTask(task: ToDoTaskEntity): Long {
                tasksFlow.update { currentTasks -> currentTasks + task }
                return task.id
            }

            override suspend fun updateTask(task: ToDoTaskEntity) {
                tasksFlow.update { currentTasks ->
                    currentTasks.map { if (it.id == task.id) task else it }
                }
            }

            override suspend fun deleteTask(task: ToDoTaskEntity) {
                tasksFlow.update { currentTasks -> currentTasks.filter { it.id != task.id } }
            }

            override suspend fun getTaskById(id: Long): ToDoTaskEntity? {
                return tasksFlow.value.firstOrNull { it.id == id }
            }
        }
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return InvalidationTracker(this, mutableMapOf(), mutableMapOf(), "ToDoTaskEntity")
    }
}