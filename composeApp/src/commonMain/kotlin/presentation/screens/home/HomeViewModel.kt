package presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.ToDoTaskEntity
import domain.repository.ToDoRepository
import domain.usecase.AddTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ToDoRepository,
    private val ioDispatcher: CoroutineDispatcher,
    private val addTaskUseCase: AddTaskUseCase,
) : ScreenModel {

    val getTasks = repository.getAllTasks()
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

    private fun addTask(task: ToDoTaskEntity) {
        screenModelScope.launch(ioDispatcher) {
            addTaskUseCase.invoke(task)
        }
    }
}