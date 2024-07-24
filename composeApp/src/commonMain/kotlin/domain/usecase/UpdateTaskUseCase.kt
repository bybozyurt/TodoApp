package domain.usecase

import domain.model.ToDoTask
import domain.repository.ToDoRepository

class UpdateTaskUseCase(
    private val repository: ToDoRepository,
) {

    suspend operator fun invoke(task: ToDoTask) {
        repository.updateTask(task)
    }

}