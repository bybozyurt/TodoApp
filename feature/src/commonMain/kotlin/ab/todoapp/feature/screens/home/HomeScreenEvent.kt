package ab.todoapp.feature.screens.home

import ab.todoapp.domain.model.ToDoTask

sealed class HomeScreenEvent {
    data class OnCheckedChange(val task: ToDoTask) : HomeScreenEvent()
    data class OnTaskClick(val id: Long) : HomeScreenEvent()
}