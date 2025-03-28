package ab.todoapp.domain.usecase

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ToDoTask

class SaveTaskUseCase(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) {
    suspend operator fun invoke(task: ToDoTask) {
        if (task.id == Constant.INVALID_TASK_ID) {
            addTaskUseCase(task)
        } else {
            updateTaskUseCase(task)
        }
    }
}