package presentation.model

import org.mongodb.kbson.ObjectId

data class ToDoTaskEntity(
    val id: ObjectId,
    val title: String,
    val description: String,
    val favorite: Boolean,
    val completed: Boolean
)