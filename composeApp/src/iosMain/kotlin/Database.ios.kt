import androidx.room.Room
import androidx.room.RoomDatabase
import common.Constant.DB_FILE_NAME
import database.ToDoDatabase
import database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilderIos(): RoomDatabase.Builder<ToDoDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_FILE_NAME"
    return Room.databaseBuilder<ToDoDatabase>(
        name = dbFilePath,
        factory = { ToDoDatabase::class.instantiateImpl() }  // IDE may show error but there is none.
    )
}
