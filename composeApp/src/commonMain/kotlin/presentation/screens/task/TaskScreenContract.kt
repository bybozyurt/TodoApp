package presentation.screens.task

import domain.model.ColorType

interface TaskScreenContract {

    data class TaskScreenUiState(
        val title: String = "",
        val description: String = "",
        val isCompleted: Boolean = false,
        val shouldNavigateToBack: Boolean = false,
        val colors: List<ColorType> = ColorType.entries,
        val selectedColor: ColorType? = ColorType.GREEN,
    )

    sealed class TaskScreenEvent {
        data class UpdateTitle(val title: String) : TaskScreenEvent()
        data class UpdateDescription(val description: String) : TaskScreenEvent()
        data class UpdateCompletedStatus(val isCompleted: Boolean) : TaskScreenEvent()
        data class UpdateTaskColor(val colorType: ColorType) : TaskScreenEvent()
        data class SaveTaskScreen(val id: Long) : TaskScreenEvent()
        data class OnDeleteTask(val id: Long) : TaskScreenEvent()
    }

    sealed interface TaskScreenSideEffect {
        data object NavigateToBack : TaskScreenSideEffect
    }
}