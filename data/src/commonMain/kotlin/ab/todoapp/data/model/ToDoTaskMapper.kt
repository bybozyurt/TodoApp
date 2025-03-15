package ab.todoapp.data.model

import ab.todoapp.domain.model.ToDoTask

fun ToDoTaskEntity.toDomain(): ToDoTask {
    return ToDoTask(
        id = this.id,
        title = this.title,
        description = this.description,
        isCompleted = this.isCompleted,
        colorType = this.colorType
    )
}

fun ToDoTask.toEntity(): ToDoTaskEntity {
    return ToDoTaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        isCompleted = this.isCompleted,
        colorType = this.colorType
    )
}