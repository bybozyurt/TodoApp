package data.mapper

import domain.model.ToDoTask
import presentation.model.ToDoTaskEntity

fun ToDoTask.toEntity() : ToDoTaskEntity {
    return ToDoTaskEntity(
        id = _id,
        title = title,
        description = description,
        completed = completed,
        favorite = favorite
    )
}

fun ToDoTaskEntity.toDomain() : ToDoTask {
    return ToDoTask().apply {
        _id = this@toDomain.id
        title = this@toDomain.title
        description = this@toDomain.description
        completed = this@toDomain.completed
        favorite = this@toDomain.favorite
    }
}