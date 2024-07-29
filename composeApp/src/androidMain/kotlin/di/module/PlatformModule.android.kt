package di.module

import data.local.ToDoDatabase
import getDatabaseBuilderAndroid
import org.koin.dsl.module

actual val platformModule = module {
    single<ToDoDatabase> {
        getDatabaseBuilderAndroid(get())
    }
}