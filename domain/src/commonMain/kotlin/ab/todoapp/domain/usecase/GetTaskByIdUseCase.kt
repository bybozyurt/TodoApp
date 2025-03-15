package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.ToDoRepository

class GetTaskByIdUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long): ToDoTask? {
        return repository.getTaskById(id)
    }
}