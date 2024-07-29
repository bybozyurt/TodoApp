package data.local

import androidx.room.*
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoTaskEntity")
    fun getAllTasks(): Flow<List<ToDoTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: ToDoTaskEntity)

    @Update
    suspend fun updateTask(task: ToDoTaskEntity)

    @Delete
    suspend fun deleteTask(task: ToDoTaskEntity)
}

