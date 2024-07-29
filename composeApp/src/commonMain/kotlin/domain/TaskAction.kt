package domain

import domain.model.ToDoTaskEntity

sealed class TaskAction {
    data class Add(val task: ToDoTaskEntity) : TaskAction()
    data class Update(val task: ToDoTaskEntity) : TaskAction()
    data class Delete(val task: ToDoTaskEntity) : TaskAction()
    data class SetCompleted(val task: ToDoTaskEntity, val completed: Boolean) : TaskAction()
    data class SetFavorite(val task: ToDoTaskEntity, val isFavorite: Boolean) : TaskAction()
}