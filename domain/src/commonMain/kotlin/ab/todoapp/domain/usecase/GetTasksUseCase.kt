package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasksUseCase(
    private val repository: ToDoRepository
) {
    operator fun invoke(): Flow<List<ToDoTask>> {
        return repository.getAllTasks().map { state ->
            state.sortedByDescending { !it.isCompleted }
        }
    }
}