package di.modules

import database.ToDoDatabase
import database.getDatabaseBuilderAndroid
import org.koin.dsl.module

actual val platformModule = module {
    single<ToDoDatabase> {
        getDatabaseBuilderAndroid(get())
    }
}