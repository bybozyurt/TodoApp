import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.local.ToDoDatabase
import data.local.dbFileName
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
