package domain.model

import domain.model.ColorType

data class ToDoTask(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val colorType: ColorType,
)