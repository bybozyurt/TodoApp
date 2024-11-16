package domain.model

import androidx.compose.ui.graphics.Color
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
    ORANGE,
    RED,
    BLUE,
    GRAY,
    PURPLE;

    companion object {
        fun ColorType.toComposeColor(): Color {
            return when (this) {
                GREEN -> Color(0xFF388E3C)
                ORANGE -> Color(0xFFFF9800)
                RED -> Color.Red
                BLUE -> Color.Blue
                PURPLE -> Color.Magenta
                GRAY -> Color.Gray
            }
        }
    }
}