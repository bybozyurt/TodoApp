package ab.todoapp.feature.home.di

import ab.todoapp.core.di.Dispatcher
import ab.todoapp.feature.home.HomeScreen
import ab.todoapp.feature.home.HomeViewModel
import ab.todoapp.navigation.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule = module {
    factory {
        HomeViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            addTaskUseCase = get(),
            getTasksUseCase = get()
        )
    }
}

val homeScreenModule = screenModule {
    register<SharedScreen.HomeScreen> {
        HomeScreen()
    }
}