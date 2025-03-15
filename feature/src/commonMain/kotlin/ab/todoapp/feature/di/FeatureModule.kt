package ab.todoapp.feature.di

import ab.todoapp.core.di.Dispatcher
import ab.todoapp.feature.screens.home.HomeViewModel
import ab.todoapp.feature.screens.task.TaskViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val featureModule = module {
    factory {
        HomeViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            addTaskUseCase = get(),
            getTasksUseCase = get()
        )
    }
    factory {
        TaskViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            addTaskUseCase = get(),
            deleteTaskUseCase = get(),
            getTaskByIdUseCase = get(),
        )
    }
}