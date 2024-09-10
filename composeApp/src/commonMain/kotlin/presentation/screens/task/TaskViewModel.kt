package presentation.screens.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.ToDoTaskEntity
import domain.usecase.AddTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val addTaskUseCase: AddTaskUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ScreenModel {

    private val _uiState = MutableStateFlow(TaskScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.UpdateTitle -> {
                _uiState.update { it.copy(title = event.title) }
            }

            is TaskEvent.UpdateDescription -> {
                _uiState.update { it.copy(description = event.description) }
            }

            is TaskEvent.UpdateCompletedStatus -> {
                _uiState.update { it.copy(isCompleted = event.isCompleted) }
            }

            is TaskEvent.SaveTask -> {

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