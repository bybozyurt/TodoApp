package di.modules

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.ToDoDatabase
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomDatabaseModule = module {
    single<ToDoDatabase> {
        provideRoomDatabase(get(), get(named(Dispatcher.IO)))
    }
}

private fun provideRoomDatabase(
    builder: RoomDatabase.Builder<ToDoDatabase>,
    dispatcher: CoroutineDispatcher,
): ToDoDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(dispatcher)
        .build()
}