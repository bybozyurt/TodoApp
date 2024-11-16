package domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val colorType: ColorType = ColorType.GREEN,
)

enum class ColorType {
    GREEN,
    YELLOW,
    RED,
    BLUE,
    GRAY,
    PURPLE,
}