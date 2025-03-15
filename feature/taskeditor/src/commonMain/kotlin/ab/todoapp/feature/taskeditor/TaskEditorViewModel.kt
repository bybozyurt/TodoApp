package ab.todoapp.feature.taskeditor

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.usecase.AddTaskUseCase
import ab.todoapp.domain.usecase.DeleteTaskUseCase
import ab.todoapp.domain.usecase.GetTaskByIdUseCase
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import ab.todoapp.feature.taskeditor.TaskScreenContract.*
import ab.todoapp.ui.delegate.mvi.MVI
import ab.todoapp.ui.delegate.mvi.mvi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TaskEditorViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
) : ScreenModel, MVI<UiState, Events, SideEffect> by mvi(UiState()) {

    fun initTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            val task = getTaskByIdUseCase(id)
            updateTask(task)
        }
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
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase(task)
            emitUiEffect(SideEffect.NavigateToBack)
        }
    }

    private fun deleteTask(id: Long) {
        screenModelScope.launch(ioDispatcher) {
            deleteTaskUseCase(id)
            emitUiEffect(SideEffect.NavigateToBack)
        }
    }
}