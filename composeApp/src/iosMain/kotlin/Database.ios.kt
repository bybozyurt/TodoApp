import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.ToDoDatabase
import database.dbFileName
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilderIos(): ToDoDatabase {
    val dbFilePath = NSHomeDirectory() + "/$dbFileName"
    return Room.databaseBuilder<ToDoDatabase>(
        name = dbFilePath,
        factory =  { ToDoDatabase::class.instantiateImpl() }  // IDE may show error but there is none.
    ).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
}
