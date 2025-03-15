package ab.todoapp.feature.taskeditor.di

import ab.todoapp.core.di.Dispatcher
import ab.todoapp.feature.screens.task.TaskEditorScreen
import ab.todoapp.feature.taskeditor.TaskEditorViewModel
import ab.todoapp.navigation.SharedScreen
import cafe.adriel.voyager.core.registry.screenModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val taskEditorModule = module {
    factory {
        TaskEditorViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            addTaskUseCase = get(),
            deleteTaskUseCase = get(),
            getTaskByIdUseCase = get(),
        )
    }
}

val taskEditorScreenModule = screenModule {
    register<SharedScreen.TaskEditorScreen> {
        TaskEditorScreen(it.id)
    }
}