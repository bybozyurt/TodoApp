package di.modules

import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.screens.home.HomeViewModel
import presentation.screens.task.TaskViewModel

val viewModelsModule = module {
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
