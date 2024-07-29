package di.modules

import database.ToDoDatabase
import getDatabaseBuilderIos
import org.koin.dsl.module

actual val platformModule = module {
    single<ToDoDatabase> {
        getDatabaseBuilderIos()
    }
}