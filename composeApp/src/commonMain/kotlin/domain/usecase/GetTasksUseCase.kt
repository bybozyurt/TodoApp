package domain.usecase

import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasksUseCase(
    private val repository: ToDoRepository
) {
    operator fun invoke(): Flow<List<ToDoTaskEntity>> {
        return repository.getAllTasks().map { state ->
            state.sortedByDescending { !it.isCompleted }
        }
    }
}