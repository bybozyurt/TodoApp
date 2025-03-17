package ab.todoapp.feature.home

import ab.todoapp.common.Constant
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ab.todoapp.feature.screens.home.ui.EmptyState
import ab.todoapp.feature.home.ui.TaskList
import ab.todoapp.ui.components.AppIconButton
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToTaskScreen: (Long) -> Unit
) {
    val tasks by viewModel.getTasks.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingButton(
                onClick = {
                    onNavigateToTaskScreen(Constant.INVALID_TASK_ID)
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
            onNavigateToTaskScreen = { id ->
                onNavigateToTaskScreen(id)
            }
        )
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
