package ab.todoapp.feature.extensions

import androidx.compose.ui.graphics.Color
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ColorType.BLUE
import ab.todoapp.domain.model.ColorType.GRAY
import ab.todoapp.domain.model.ColorType.GREEN
import ab.todoapp.domain.model.ColorType.ORANGE
import ab.todoapp.domain.model.ColorType.PURPLE
import ab.todoapp.domain.model.ColorType.RED

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