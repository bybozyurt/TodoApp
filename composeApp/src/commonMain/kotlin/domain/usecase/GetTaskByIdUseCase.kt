package domain.usecase

import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository

class GetTaskByIdUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long): ToDoTaskEntity? {
        return repository.getTaskById(id)
    }
}