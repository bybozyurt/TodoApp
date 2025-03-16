package ab.todoapp.feature.taskeditor.navigation

import ab.todoapp.feature.taskeditor.TaskEditorScreen
import ab.todoapp.feature.taskeditor.TaskEditorViewModel
import ab.todoapp.ui.navigation.Screen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class TaskEditor(val id : Long) : Screen

fun NavGraphBuilder.taskEditorScreen(
    onNavigateToBack: () -> Unit
) {
    composable<TaskEditor> {
        val viewModel = koinViewModel<TaskEditorViewModel>()
        TaskEditorScreen(
            viewModel = viewModel,
            navigateToBack = onNavigateToBack
        )
    }
}