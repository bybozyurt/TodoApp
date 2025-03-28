package ab.todoapp.feature.home

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.usecase.GetTasksUseCase
import ab.todoapp.domain.usecase.SaveTaskUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ab.todoapp.feature.home.HomeScreenContract.Event

class HomeViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    val getTasks = getTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun onEvent(event: Event) {
        if (event is Event.OnCheckedChange) {
            addTask(event.task)
            return
        }
    }

    private fun addTask(task: ToDoTask) {
        viewModelScope.launch(ioDispatcher) {
            saveTaskUseCase(task)
        }
    }
}