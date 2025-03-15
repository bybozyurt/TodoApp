package presentation.screens.task

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import presentation.screens.task.TaskScreenContract.*
import domain.model.ToDoTask
import domain.usecase.AddTaskUseCase
import domain.usecase.DeleteTaskUseCase
import domain.usecase.GetTaskByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import presentation.delegate.mvi.MVI
import presentation.delegate.mvi.mvi

class TaskViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
) : ScreenModel, MVI<TaskScreenUiState, TaskScreenEvent, TaskScreenSideEffect> by mvi(TaskScreenUiState()) {

    fun initTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            val task = getTaskByIdUseCase(id)
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
                    ToDoTask(
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

    private fun updateTask(task: ToDoTask?) {
        updateUiState {
            copy(
                title = task?.title.orEmpty(),
                description = task?.description.orEmpty(),
                isCompleted = task?.isCompleted == true,
                selectedColor = task?.colorType
            )
        }
    }

    private fun addTask(task: ToDoTask) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase(task)
            emitUiEffect(TaskScreenSideEffect.NavigateToBack)
        }
    }

    private fun deleteTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            deleteTaskUseCase(id)
            emitUiEffect(TaskScreenSideEffect.NavigateToBack)
        }
    }
}