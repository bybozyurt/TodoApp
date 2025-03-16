package ab.todoapp.feature.home

import ab.todoapp.domain.model.ToDoTask

interface HomeScreenContract {
    sealed class Event {
        data class OnCheckedChange(val task: ToDoTask) : Event()
        data class OnTaskClick(val id: Long) : Event()
    }
}
