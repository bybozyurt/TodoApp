package di.modules

import org.koin.core.qualifier.named
import org.koin.dsl.module
import presentation.screens.home.HomeViewModel
import presentation.screens.task.TaskViewModel

val viewModelsModule = module {
    factory { HomeViewModel(get(), get(named(Dispatcher.IO))) }
    factory { TaskViewModel(get(), get(named(Dispatcher.IO))) }
}
