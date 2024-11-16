package presentation.screens.task

import domain.model.ColorType

sealed class TaskScreenEvent {
    data class UpdateTitle(val title: String) : TaskScreenEvent()
    data class UpdateDescription(val description: String) : TaskScreenEvent()
    data class UpdateCompletedStatus(val isCompleted: Boolean) : TaskScreenEvent()
    data class UpdateTaskColor(val colorType: ColorType) : TaskScreenEvent()
    data class SaveTaskScreen(val id: Long) : TaskScreenEvent()
}