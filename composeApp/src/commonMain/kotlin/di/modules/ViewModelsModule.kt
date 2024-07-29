package di.modules

import org.koin.dsl.module
import presentation.home.HomeViewModel
import presentation.task.TaskViewModel

val viewModelsModule = module {
    factory { HomeViewModel(get()) }
    factory { TaskViewModel() }
}
