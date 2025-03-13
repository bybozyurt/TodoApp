package presentation.screens.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import presentation.screens.task.TaskScreenContract.*
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import domain.usecase.AddTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import presentation.delegate.mvi.MVI
import presentation.delegate.mvi.mvi

class TaskViewModel(
    private val repository: ToDoRepository,
    private val addTaskUseCase: AddTaskUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ScreenModel, MVI<TaskScreenUiState, TaskScreenEvent, TaskScreenSideEffect> by mvi(TaskScreenUiState()) {

    fun initTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            val task = repository.getTaskById(id)
            updateTask(task)
        }
    }

    override fun onAction(uiAction: TaskScreenEvent) {
        when(uiAction) {
            is TaskScreenEvent.UpdateTitle -> {
                updateUiState { copy(title = uiAction.title) }
            }

            is TaskScreenEvent.UpdateDescription -> {
                updateUiState { copy(description = uiAction.description) }
            }

            is TaskScreenEvent.UpdateCompletedStatus -> {
                updateUiState { copy(isCompleted = uiAction.isCompleted) }
            }

            is TaskScreenEvent.UpdateTaskColor -> {
                updateUiState { copy(selectedColor = uiAction.colorType) }
            }

            is TaskScreenEvent.SaveTaskScreen -> {
                addTask(
                    ToDoTaskEntity(
                        id = uiAction.id,
                        title = currentUiState.title,
                        description = currentUiState.description,
                        isCompleted = currentUiState.isCompleted,
                        colorType = currentUiState.selectedColor!!,
                    )
                )
            }

            is TaskScreenEvent.OnDeleteTask -> {
                deleteTask(uiAction.id)
            }
        }
    }

    private fun updateTask(task: ToDoTaskEntity?) {
        updateUiState {
            copy(
                title = task?.title.orEmpty(),
                description = task?.description.orEmpty(),
                isCompleted = task?.isCompleted == true,
                selectedColor = task?.colorType
            )
        }
    }

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase.invoke(task)
            emitUiEffect(TaskScreenSideEffect.NavigateToBack)
        }
    }

    private fun deleteTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            repository.deleteTask(id)
            emitUiEffect(TaskScreenSideEffect.NavigateToBack)
        }
    }
}