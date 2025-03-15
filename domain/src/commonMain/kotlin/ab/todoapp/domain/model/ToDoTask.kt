package ab.todoapp.domain.model

data class ToDoTask(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val colorType: ColorType,
)