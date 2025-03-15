package ab.todoapp.feature.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.components.AppIconButton
import ab.todoapp.feature.screens.home.ui.EmptyState
import ab.todoapp.feature.screens.home.ui.TaskList
import ab.todoapp.feature.screens.task.TaskScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlin.random.Random

class HomeScreen : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<HomeViewModel>()
        val tasks by viewModel.getTasks.collectAsStateWithLifecycle()

        Scaffold(
            floatingActionButton = {
                FloatingButton(
                    onClick = {
                        navigator.push(TaskScreen())
                    }
                )
            }
        ) { paddingValues ->
            if (tasks.isNullOrEmpty()) {
                EmptyState()
                return@Scaffold
            }
            TaskList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                tasks = tasks,
                viewModel = viewModel,
            )
        }
    }
}

@Composable
private fun FloatingButton(
    onClick: () -> Unit = {}
) {
    AppIconButton(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
        imageVector = Icons.Filled.Add,
        onClick = onClick,
        tintColor = MaterialTheme.colorScheme.primary,
    )
}
