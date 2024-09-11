package presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ToDoRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ScreenModel {

    val getTasks = repository.getAllTasks()
        .map { state ->
            state.getSuccessData().sortedByDescending { it.isCompleted }
        }
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun onEvent(event: HomeScreenEvent) {
        if (event is HomeScreenEvent.OnCheckedChange) {
            updateTask(event.task)
        }

        if (event is HomeScreenEvent.OnDeleteTask) {
            deleteTask(event.task)
        }
    }

    private fun deleteTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            repository.deleteTask(task)
        }
    }

    private fun updateTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            repository.updateTask(task)
        }
    }
}