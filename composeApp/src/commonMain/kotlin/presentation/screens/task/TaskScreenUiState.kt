package presentation.screens.task

import domain.model.ColorType

data class TaskScreenUiState(
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val shouldNavigateToBack: Boolean = false,
    val colors: List<ColorType> = ColorType.entries,
    val selectedColor: ColorType? = ColorType.GREEN,
)
