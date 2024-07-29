package domain.usecase

import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository

class AddTaskUseCase(
    private val repository: ToDoRepository,
) {
    suspend operator fun invoke(task: ToDoTaskEntity) {
        repository.addTask(task)
    }
}