package presentation.screens.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.TaskAction
import domain.model.ToDoTaskEntity
import domain.usecase.AddTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TaskViewModel(
    private val addTaskUseCase: AddTaskUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ScreenModel {

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

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            addTask(task)
        }
    }

    private fun updateTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            //repository.updateTask(task)
        }
    }
}