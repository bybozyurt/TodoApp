package presentation.screens.home

import domain.model.ToDoTask

sealed class HomeScreenEvent {
    data class OnCheckedChange(val task: ToDoTask) : HomeScreenEvent()
    data class OnTaskClick(val id: Long) : HomeScreenEvent()
}