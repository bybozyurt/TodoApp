package domain

import domain.model.ToDoTask
import domain.repository.ToDoRepository

class AddTaskUseCase(
    private val repository: ToDoRepository,
) {
    suspend operator fun invoke(task: ToDoTask) {
        repository.addTask(task)
    }
}