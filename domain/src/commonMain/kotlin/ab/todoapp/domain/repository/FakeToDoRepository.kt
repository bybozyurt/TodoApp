package ab.todoapp.domain.repository

import ab.todoapp.domain.model.ToDoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeToDoRepository : ToDoRepository {

    private val _tasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val tasks = _tasks.asStateFlow()

    override fun getAllTasks() = tasks

    override suspend fun addTask(task: ToDoTask) {
        _tasks.update { it + task }
    }

    override suspend fun updateTask(task: ToDoTask) {
        _tasks.update { it.map { t -> if (t.id == task.id) task else t } }
    }

    override suspend fun deleteTask(id: Long) {
        _tasks.update { it.filterNot { it.id == id } }
    }

    override suspend fun getTaskById(id: Long): ToDoTask? {
        return _tasks.value.firstOrNull { it.id == id }
    }
}