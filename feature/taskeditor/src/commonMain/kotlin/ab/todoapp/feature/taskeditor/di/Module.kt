package ab.todoapp.feature.taskeditor.di

import ab.todoapp.common.di.Dispatcher
import ab.todoapp.feature.taskeditor.TaskEditorViewModel
import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val taskEditorModule = module {
    viewModel {(handle: SavedStateHandle) ->
        TaskEditorViewModel(
            ioDispatcher = get(named(Dispatcher.IO)),
            saveTaskUseCase = get(),
            deleteTaskUseCase = get(),
            getTaskByIdUseCase = get(),
            savedStateHandle = handle,
        )
    }
}
