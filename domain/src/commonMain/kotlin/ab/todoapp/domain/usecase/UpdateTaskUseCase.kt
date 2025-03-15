package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.ToDoRepository

class UpdateTaskUseCase(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(task: ToDoTask) {
        repository.updateTask(task)
    }
}