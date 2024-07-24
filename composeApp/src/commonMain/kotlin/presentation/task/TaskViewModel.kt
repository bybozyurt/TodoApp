package presentation.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.AddTaskUseCase
import domain.TaskAction
import domain.model.ToDoTask
import domain.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: ToDoRepository,
    private val addTaskUseCase: AddTaskUseCase,
): ScreenModel {

    fun setAction(action: TaskAction) {
        when (action) {
            is TaskAction.Add -> {
                addTask(action.task)
            }

            is TaskAction.Update -> {
                updateTask(action.task)
            }

            else -> {
                // no-op
            }
        }
    }

    private fun addTask(task: ToDoTask) {
        screenModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.invoke(task)
        }
    }

    private fun updateTask(task: ToDoTask) {
        screenModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }
}