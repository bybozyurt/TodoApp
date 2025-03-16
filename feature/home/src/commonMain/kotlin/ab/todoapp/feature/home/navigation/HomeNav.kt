package ab.todoapp.feature.home.navigation

import ab.todoapp.feature.home.HomeScreen
import ab.todoapp.feature.home.HomeViewModel
import ab.todoapp.ui.navigation.Screen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object Home : Screen

fun NavGraphBuilder.homeScreen(
    onNavigateToTaskScreen: (Long) -> Unit
) {
    composable<Home> {
        val viewModel = koinViewModel<HomeViewModel>()

        HomeScreen(
            viewModel = viewModel,
            onNavigateToTaskScreen = onNavigateToTaskScreen
        )
    }
}