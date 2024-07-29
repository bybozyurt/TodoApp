package module

import data.local.ToDoDatabase
import getDatabaseBuilderIos
import org.koin.dsl.module

actual val platformModule = module {
    single<ToDoDatabase> {
        getDatabaseBuilderIos()
    }
}