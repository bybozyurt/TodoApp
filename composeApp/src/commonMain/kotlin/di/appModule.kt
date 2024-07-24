package di

import data.repository.ToDoRepositoryImpl
import domain.usecase.AddTaskUseCase
import domain.repository.ToDoRepository
import org.koin.dsl.module
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val appModule = module {
    factory { HomeViewModel(get()) }
    factory { TaskViewModel(get()) }
    single { provideToDoRepository() }
    factory { provideAddTaskUseCase(get()) }
}

private fun provideToDoRepository(): ToDoRepository = ToDoRepositoryImpl()

private fun provideAddTaskUseCase(toDoRepository: ToDoRepository): AddTaskUseCase =
    AddTaskUseCase(toDoRepository)