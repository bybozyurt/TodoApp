package presentation.screens.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import domain.usecase.AddTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: ToDoRepository,
    private val addTaskUseCase: AddTaskUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ScreenModel {

    private val _uiState = MutableStateFlow(TaskScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun navigateToBack(isAdded: Boolean) {
        _uiState.update { it.copy(shouldNavigateToBack = isAdded) }
    }

    fun initTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            val task = repository.getTaskById(id)
            _uiState.update {
                it.copy(
                    title = task?.title.orEmpty(),
                    description = task?.description.orEmpty(),
                    isCompleted = task?.isCompleted == true,
                    selectedColor = task?.colorType
                )
            }
        }
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

            is TaskScreenEvent.UpdateTaskColor -> {
                _uiState.update { it.copy(selectedColor = event.colorType) }
            }

            is TaskScreenEvent.SaveTaskScreen -> {
                addTask(
                    ToDoTaskEntity(
                        id = event.id,
                        title = _uiState.value.title,
                        description = _uiState.value.description,
                        isCompleted = _uiState.value.isCompleted,
                        colorType = _uiState.value.selectedColor!!,
                    )
                )
            }

            is TaskScreenEvent.OnDeleteTask -> {
                deleteTask(event.id)
            }
        }
    }

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase.invoke(task)
        }
        navigateToBack(true)
    }

    private fun deleteTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            repository.deleteTask(id)
        }
        navigateToBack(true)
    }
}