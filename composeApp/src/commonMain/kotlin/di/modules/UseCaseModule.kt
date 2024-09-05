package di.modules

import domain.usecase.AddTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddTaskUseCase(get()) }
}