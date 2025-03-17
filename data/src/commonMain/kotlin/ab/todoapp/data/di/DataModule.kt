package ab.todoapp.data.di

import ab.todoapp.common.di.Dispatcher
import ab.todoapp.data.source.local.LocalDataSource
import ab.todoapp.data.source.local.LocalDataSourceImpl
import ab.todoapp.data.repository.ToDoRepositoryImpl
import ab.todoapp.data.database.ToDoDatabase
import ab.todoapp.domain.repository.ToDoRepository
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single<ToDoRepository> { ToDoRepositoryImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get(), get(named(Dispatcher.IO))) }
    single<ToDoDatabase> {
        provideRoomDatabase(get(), get(named(Dispatcher.IO)))
    }
} + platformModule

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