package domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
)