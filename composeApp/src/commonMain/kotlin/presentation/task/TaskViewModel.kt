package presentation.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.TaskAction
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TaskViewModel(
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

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(Dispatchers.IO) {
            //repository.addTask(task)
        }
    }

    private fun updateTask(task: ToDoTaskEntity) {
        screenModelScope.launch(Dispatchers.IO) {
            //repository.updateTask(task)
        }
    }
}