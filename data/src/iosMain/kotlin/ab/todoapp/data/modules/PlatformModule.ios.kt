package ab.todoapp.data.modules

import ab.todoapp.data.database.ToDoDatabase
import androidx.room.RoomDatabase
import ab.todoapp.data.getDatabaseBuilderIos
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<ToDoDatabase>> {
        getDatabaseBuilderIos()
    }
}