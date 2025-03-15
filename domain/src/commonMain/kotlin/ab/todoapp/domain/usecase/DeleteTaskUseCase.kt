package ab.todoapp.domain.usecase

import ab.todoapp.domain.repository.ToDoRepository

class DeleteTaskUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteTask(id)
    }
}