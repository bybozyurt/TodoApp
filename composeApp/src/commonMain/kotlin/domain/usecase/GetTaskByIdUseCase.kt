package domain.usecase

import data.model.ToDoTaskEntity
import domain.model.ToDoTask
import domain.repository.ToDoRepository

class GetTaskByIdUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long): ToDoTask? {
        return repository.getTaskById(id)
    }
}