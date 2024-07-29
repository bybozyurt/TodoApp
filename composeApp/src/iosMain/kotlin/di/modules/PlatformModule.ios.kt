package di.modules

import androidx.room.RoomDatabase
import database.ToDoDatabase
import getDatabaseBuilderIos
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<ToDoDatabase>> {
        getDatabaseBuilderIos()
    }
}