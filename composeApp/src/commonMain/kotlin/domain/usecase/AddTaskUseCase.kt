package domain.usecase

import common.Constant
import data.model.ToDoTaskEntity
import domain.model.ToDoTask
import domain.repository.ToDoRepository

class AddTaskUseCase(
    private val repository: ToDoRepository,
    private val updateTaskUseCase: UpdateTaskUseCase,
) {
    suspend operator fun invoke(task: ToDoTask) {
        if (task.id == Constant.INVALID_TASK_ID) {
            repository.addTask(task)
            return
        }
        updateTaskUseCase(task)
    }
}