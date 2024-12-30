package di.modules

import data.local.LocalDataSource
import data.local.LocalDataSourceImpl
import data.repository.ToDoRepositoryImpl
import domain.repository.ToDoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<ToDoRepository> { ToDoRepositoryImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get(), get(named(Dispatcher.IO))) }
}
