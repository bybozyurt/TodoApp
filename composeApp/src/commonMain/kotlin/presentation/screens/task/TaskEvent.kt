package presentation.screens.task

sealed class TaskEvent {
    data class UpdateTitle(val title: String) : TaskEvent()
    data class UpdateDescription(val description: String) : TaskEvent()
    data class UpdateCompletedStatus(val isCompleted: Boolean) : TaskEvent()
    data object SaveTask : TaskEvent()
}