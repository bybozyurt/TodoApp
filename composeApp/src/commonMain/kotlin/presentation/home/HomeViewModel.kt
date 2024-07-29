package presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.local.ToDoDao
import domain.RequestState
import domain.TaskAction
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias MutableTasks = MutableState<RequestState<List<ToDoTaskEntity>>>
typealias Tasks = MutableState<RequestState<List<ToDoTaskEntity>>>

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
        }
        screenModelScope.launch(Dispatchers.Main) {
            delay(500)
            repository.getAllTasks().collectLatest {
                _completedTasks.value = it
            }
        }
    }

    fun initDao(dao: ToDoDao) {
        repository.initDao(dao)
    }

    fun setAction(action: TaskAction) {
        when (action) {
            is TaskAction.Delete -> {
                deleteTask(action.task)
            }

            is TaskAction.SetCompleted -> {

            }

            is TaskAction.SetFavorite -> {
            }

            else -> {}
        }
    }

    private fun deleteTask(task: ToDoTaskEntity) {
        screenModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}