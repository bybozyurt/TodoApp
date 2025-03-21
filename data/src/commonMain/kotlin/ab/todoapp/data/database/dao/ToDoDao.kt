package ab.todoapp.data.database.dao

import ab.todoapp.data.model.ToDoTaskEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoTaskEntity")
    fun getAllTasks(): Flow<List<ToDoTaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: ToDoTaskEntity): Long

    @Update
    suspend fun updateTask(task: ToDoTaskEntity)

    @Delete
    suspend fun deleteTask(task: ToDoTaskEntity)

    @Query("SELECT * FROM ToDoTaskEntity WHERE id = :id")
    suspend fun getTaskById(id: Long): ToDoTaskEntity?
}

