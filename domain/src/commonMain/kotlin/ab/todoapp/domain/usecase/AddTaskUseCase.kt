package ab.todoapp.domain.usecase

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.ToDoRepository

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