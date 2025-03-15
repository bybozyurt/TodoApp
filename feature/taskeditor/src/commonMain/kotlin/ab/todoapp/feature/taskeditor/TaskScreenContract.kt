package ab.todoapp.feature.taskeditor

import ab.todoapp.domain.model.ColorType

interface TaskScreenContract {

    data class UiState(
        val title: String = "",
        val description: String = "",
        val isCompleted: Boolean = false,
        val shouldNavigateToBack: Boolean = false,
        val colors: List<ColorType> = ColorType.entries,
        val selectedColor: ColorType? = ColorType.GREEN,
    )

    sealed class Events {
        data class UpdateTitle(val title: String) : Events()
        data class UpdateDescription(val description: String) : Events()
        data class UpdateCompletedStatus(val isCompleted: Boolean) : Events()
        data class UpdateTaskColor(val colorType: ColorType) : Events()
        data class SaveTaskScreen(val id: Long) : Events()
        data class OnDeleteTask(val id: Long) : Events()
    }

    sealed interface SideEffect {
        data object NavigateToBack : SideEffect
    }
}