import androidx.room.Room
import androidx.room.RoomDatabase
import database.ToDoDatabase
import database.dbFileName
import database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilderIos(): RoomDatabase.Builder<ToDoDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$dbFileName"
    return Room.databaseBuilder<ToDoDatabase> (
        name = dbFilePath,
        factory =  { ToDoDatabase::class.instantiateImpl() }  // IDE may show error but there is none.
    )
}
