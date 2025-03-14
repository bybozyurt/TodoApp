package di.modules

import domain.usecase.AddTaskUseCase
import domain.usecase.DeleteTaskUseCase
import domain.usecase.GetTaskByIdUseCase
import domain.usecase.GetTasksUseCase
import domain.usecase.UpdateTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddTaskUseCase(get(), get()) }
    factory { GetTasksUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { GetTaskByIdUseCase(get()) }
    factory { UpdateTaskUseCase(get()) }
}