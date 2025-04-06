package ab.todoapp.feature.taskeditor

import ab.todoapp.common.Constant.INVALID_TASK_ID
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.usecase.DeleteTaskUseCase
import ab.todoapp.domain.usecase.GetTaskByIdUseCase
import ab.todoapp.domain.usecase.SaveTaskUseCase
import ab.todoapp.feature.taskeditor.TaskScreenContract.*
import ab.todoapp.ui.delegate.mvi.MVI
import ab.todoapp.ui.delegate.mvi.mvi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TaskEditorViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), MVI<UiState, Events, SideEffect> by mvi(UiState()) {

    var taskId: Long = 0L

    private fun initTask() {
        if (taskId == INVALID_TASK_ID) {
            return
        }
        viewModelScope.launch(ioDispatcher) {
            val task = getTaskByIdUseCase(taskId)
            updateTask(task)
        }
    }

    init {
        taskId = savedStateHandle["id"] ?: INVALID_TASK_ID
        initTask()
    }

    override fun onAction(uiAction: Events) {
        when(uiAction) {
            is Events.UpdateTitle -> {
                updateUiState { copy(title = uiAction.title) }
            }

            is Events.UpdateDescription -> {
                updateUiState { copy(description = uiAction.description) }
            }

            is Events.UpdateCompletedStatus -> {
                updateUiState { copy(isCompleted = uiAction.isCompleted) }
            }

            is Events.UpdateTaskColor -> {
                updateUiState { copy(selectedColor = uiAction.colorType) }
            }

            is Events.SaveTaskScreen -> {
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

            is Events.OnDeleteTask -> {
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
        viewModelScope.launch(ioDispatcher) {
            saveTaskUseCase(task)
            emitUiEffect(SideEffect.NavigateToBack)
        }
    }

    private fun deleteTask(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            deleteTaskUseCase(id)
            emitUiEffect(SideEffect.NavigateToBack)
        }
    }
}