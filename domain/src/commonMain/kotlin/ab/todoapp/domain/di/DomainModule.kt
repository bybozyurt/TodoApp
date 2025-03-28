package ab.todoapp.domain.di

import ab.todoapp.domain.usecase.AddTaskUseCase
import ab.todoapp.domain.usecase.DeleteTaskUseCase
import ab.todoapp.domain.usecase.GetTaskByIdUseCase
import ab.todoapp.domain.usecase.GetTasksUseCase
import ab.todoapp.domain.usecase.SaveTaskUseCase
import ab.todoapp.domain.usecase.UpdateTaskUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { AddTaskUseCase(get()) }
    factory { GetTasksUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { GetTaskByIdUseCase(get()) }
    factory { UpdateTaskUseCase(get()) }
    factory { SaveTaskUseCase(get(), get()) }
}