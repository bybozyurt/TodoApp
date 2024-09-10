package presentation.screens.task

data class TaskScreenUiState(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
)
