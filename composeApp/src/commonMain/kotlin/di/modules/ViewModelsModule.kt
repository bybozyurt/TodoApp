package di.modules

import org.koin.dsl.module
import presentation.screens.home.HomeViewModel
import presentation.screens.task.TaskViewModel

val viewModelsModule = module {
    factory { HomeViewModel(get()) }
    factory { TaskViewModel() }
}
