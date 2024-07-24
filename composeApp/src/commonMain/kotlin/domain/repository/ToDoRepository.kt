package domain.repository

import domain.RequestState
import domain.model.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun configureTheRealm()

    fun getFavoriteTasks(): Flow<RequestState<List<ToDoTask>>>

    fun getCompletedTasks(): Flow<RequestState<List<ToDoTask>>>

    suspend fun addTask(task: ToDoTask)

    suspend fun updateTask(task: ToDoTask)

    suspend fun setCompleted(task: ToDoTask, taskCompleted: Boolean)

    suspend fun setFavorite(task: ToDoTask, isFavorite: Boolean)

    suspend fun deleteTask(task: ToDoTask)

}