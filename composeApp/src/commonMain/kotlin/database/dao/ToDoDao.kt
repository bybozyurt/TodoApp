package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
