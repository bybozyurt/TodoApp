package ab.todoapp.data.database

import ab.todoapp.data.model.ToDoTaskEntity
import ab.todoapp.data.database.dao.ToDoDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ToDoTaskEntity::class], version = 2)
@TypeConverters(ColorTypeConverter::class)
abstract class ToDoDatabase : RoomDatabase(), DB {
    abstract fun toDoDao(): ToDoDao

    override fun clearAllTables() {
        println("clearAllTables()")
        super.clearAllTables()
    }
}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}