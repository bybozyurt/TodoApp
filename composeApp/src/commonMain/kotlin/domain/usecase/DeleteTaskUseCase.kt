package domain.usecase

import domain.repository.ToDoRepository

class DeleteTaskUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteTask(id)
    }
}