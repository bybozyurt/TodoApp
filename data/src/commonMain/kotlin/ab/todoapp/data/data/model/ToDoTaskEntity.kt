package ab.todoapp.data.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ab.todoapp.domain.model.ColorType

@Entity
data class ToDoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val colorType: ColorType = ColorType.GREEN,
)
