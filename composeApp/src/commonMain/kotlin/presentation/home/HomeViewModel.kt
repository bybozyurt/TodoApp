package presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.RequestState
import domain.TaskAction
import domain.model.ToDoTask
import domain.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias MutableTasks = MutableState<RequestState<List<ToDoTask>>>
typealias Tasks = MutableState<RequestState<List<ToDoTask>>>

class HomeViewModel(
    private val repository: ToDoRepository,
) : ScreenModel {
    private var _activeTasks: MutableTasks = mutableStateOf(RequestState.Idle)
    val activeTasks: Tasks = _activeTasks

    private var _completedTasks: MutableTasks = mutableStateOf(RequestState.Idle)
    val completedTasks: Tasks = _completedTasks

    init {
        _activeTasks.value = RequestState.Loading
        _completedTasks.value = RequestState.Loading
        screenModelScope.launch(Dispatchers.Main) {
            delay(500)
            repository.getFavoriteTasks().collectLatest {
                _activeTasks.value = it
            }
        }
        screenModelScope.launch(Dispatchers.Main) {
            delay(500)
            repository.getCompletedTasks().collectLatest {
                _completedTasks.value = it
            }
        }
    }

    fun setAction(action: TaskAction) {
        when (action) {
            is TaskAction.Delete -> {
                deleteTask(action.task)
            }

            is TaskAction.SetCompleted -> {
                setCompleted(action.task, action.completed)
            }

            is TaskAction.SetFavorite -> {
                setFavorite(action.task, action.isFavorite)
            }

            else -> {}
        }
    }

    private fun setCompleted(task: ToDoTask, completed: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            repository.setCompleted(task, completed)
        }
    }

    private fun setFavorite(task: ToDoTask, isFavorite: Boolean) {
        screenModelScope.launch(Dispatchers.IO) {
            repository.setFavorite(task, isFavorite)
        }
    }

    private fun deleteTask(task: ToDoTask) {
        screenModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}