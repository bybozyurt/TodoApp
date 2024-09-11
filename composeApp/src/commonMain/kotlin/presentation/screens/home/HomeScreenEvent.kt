package presentation.screens.home

import domain.model.ToDoTaskEntity

sealed class HomeScreenEvent {
    data class OnCheckedChange(val task: ToDoTaskEntity) : HomeScreenEvent()
    data class OnDeleteTask(val task: ToDoTaskEntity) : HomeScreenEvent()
    data class OnTaskClick(val id: Long) : HomeScreenEvent()
}