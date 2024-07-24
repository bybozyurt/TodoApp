package di

import data.MongoDB
import data.repository.ToDoRepositoryImpl
import org.koin.dsl.module
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val appModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
    single { ToDoRepositoryImpl() }
}