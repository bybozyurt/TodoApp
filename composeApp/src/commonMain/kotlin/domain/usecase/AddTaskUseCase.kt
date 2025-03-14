package domain.usecase

import common.Constant
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository

class AddTaskUseCase(
    private val repository: ToDoRepository,
    private val updateTaskUseCase: UpdateTaskUseCase,
) {
    suspend operator fun invoke(task: ToDoTaskEntity) {
        if (task.id == Constant.INVALID_TASK_ID) {
            repository.addTask(task)
            return
        }
        updateTaskUseCase(task)
    }
}