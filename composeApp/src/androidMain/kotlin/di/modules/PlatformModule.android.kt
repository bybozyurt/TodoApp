package di.modules

import androidx.room.RoomDatabase
import database.ToDoDatabase
import database.getDatabaseBuilderAndroid
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<ToDoDatabase>> {
        getDatabaseBuilderAndroid(get())
    }
}