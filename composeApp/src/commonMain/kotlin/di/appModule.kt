package di

import data.repository.ToDoRepositoryImpl
import domain.AddTaskUseCase
import domain.repository.ToDoRepository
import org.koin.dsl.module
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val appModule = module {
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
    single { provideToDoRepository() }
    factory { AddTaskUseCase(get()) }
}

private fun provideToDoRepository(): ToDoRepository = ToDoRepositoryImpl()