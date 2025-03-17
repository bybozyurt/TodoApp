package ab.todoapp.feature.home.di

import ab.todoapp.common.di.Dispatcher
import ab.todoapp.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            addTaskUseCase = get(),
            getTasksUseCase = get()
        )
    }
}
