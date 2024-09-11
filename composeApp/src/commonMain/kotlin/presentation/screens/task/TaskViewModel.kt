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

    fun updateIsTaskAdded(isAdded: Boolean) {
        _uiState.update { it.copy(isTaskAdded = isAdded) }
    }

    fun onEvent(event: TaskScreenEvent) {
        when (event) {
            is TaskScreenEvent.UpdateTitle -> {
                _uiState.update { it.copy(title = event.title) }
            }

            is TaskScreenEvent.UpdateDescription -> {
                _uiState.update { it.copy(description = event.description) }
            }

            is TaskScreenEvent.UpdateCompletedStatus -> {
                _uiState.update { it.copy(isCompleted = event.isCompleted) }
            }

            is TaskScreenEvent.SaveTaskScreen -> {
                addTask(
                    ToDoTaskEntity(
                        title = _uiState.value.title,
                        description = _uiState.value.description,
                        isCompleted = _uiState.value.isCompleted,
                    )
                )
            }
        }
    }

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase.invoke(task)
        }
        updateIsTaskAdded(true)
    }

    private fun updateTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            //repository.updateTask(task)
        }
    }
}