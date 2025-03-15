package ab.todoapp.data

import ab.todoapp.data.database.ToDoDatabase
import ab.todoapp.data.database.instantiateImpl
import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilderIos(): RoomDatabase.Builder<ToDoDatabase> {
    val dbFilePath = NSHomeDirectory() + "/todo.db"
    return Room.databaseBuilder<ToDoDatabase>(
        name = dbFilePath,
        factory = { ToDoDatabase::class.instantiateImpl() }  // IDE may show error but there is none.
    )
}
