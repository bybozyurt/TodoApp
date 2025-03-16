package ab.todoapp.navigation

import ab.todoapp.feature.home.navigation.Home
import ab.todoapp.feature.home.navigation.homeScreen
import ab.todoapp.feature.taskeditor.navigation.TaskEditor
import ab.todoapp.feature.taskeditor.navigation.taskEditorScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = Home,
    ) {
        homeScreen(
            onNavigateToTaskScreen = {
                navController.navigate(TaskEditor(it))
            }
        )
        taskEditorScreen(
            onNavigateToBack = {
                navController.navigateUp()
            }
        )
    }
}