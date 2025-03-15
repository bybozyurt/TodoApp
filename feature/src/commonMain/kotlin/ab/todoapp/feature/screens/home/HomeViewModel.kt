package ab.todoapp.feature.screens.home

import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.usecase.AddTaskUseCase
import ab.todoapp.domain.usecase.GetTasksUseCase
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val addTaskUseCase: AddTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
) : ScreenModel {

    val getTasks = getTasksUseCase()
        .stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun onEvent(event: HomeScreenEvent) {
        if (event is HomeScreenEvent.OnCheckedChange) {
            addTask(event.task)
            return
        }
    }

    private fun addTask(task: ToDoTask) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase(task)
        }
    }
}