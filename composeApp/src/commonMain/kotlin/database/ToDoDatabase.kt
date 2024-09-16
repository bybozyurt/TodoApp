package database

import androidx.room.Database
import androidx.room.RoomDatabase
import database.dao.ToDoDao
import domain.model.ToDoTaskEntity

@Database(entities = [ToDoTaskEntity::class], version = 2)
abstract class ToDoDatabase : RoomDatabase(), DB {
    abstract fun toDoDao(): ToDoDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}