package ab.todoapp.data.di

import ab.todoapp.data.database.ToDoDatabase
import androidx.room.RoomDatabase
import ab.todoapp.data.database.getDatabaseBuilderAndroid
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<ToDoDatabase>> {
        getDatabaseBuilderAndroid(get())
    }
}