package ab.todoapp.feature.home

import ab.todoapp.domain.model.ToDoTask

sealed class HomeScreenEvent {
    data class OnCheckedChange(val task: ToDoTask) : HomeScreenEvent()
    data class OnTaskClick(val id: Long) : HomeScreenEvent()
}