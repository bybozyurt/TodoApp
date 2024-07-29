package di.module

import data.repository.ToDoRepositoryImpl
import domain.usecase.AddTaskUseCase
import domain.repository.ToDoRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val appModule = module {
    factory { HomeViewModel(get()) }
    factory { TaskViewModel() }
    single { provideToDoRepository() }
    factory { provideAddTaskUseCase(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            appModule,
            platformModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}

private fun provideToDoRepository(): ToDoRepository = ToDoRepositoryImpl()

private fun provideAddTaskUseCase(toDoRepository: ToDoRepository): AddTaskUseCase =
    AddTaskUseCase(toDoRepository)

