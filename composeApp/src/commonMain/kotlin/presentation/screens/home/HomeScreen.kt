package presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.onClick
import common.onHomeEvent
import domain.model.ToDoTaskEntity
import presentation.components.AppIcon
import presentation.components.AppIconButton
import presentation.screens.task.TaskScreen
import kotlin.random.Random

class HomeScreen : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<HomeViewModel>()
        val tasks by viewModel.getTasks.collectAsState()

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(
                        items = tasks!!,
                        key = { it.id }
                    ) { task ->
                        TaskItemView(
                            modifier = Modifier
                                .animateItemPlacement(),
                            task = task,
                            onEvent = lambda@{ event ->
                                if (event is HomeScreenEvent.OnTaskClick) {
                                    navigator.push(TaskScreen(id = event.id))
                                    return@lambda
                                }
                                viewModel.onEvent(event)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Add an illustration or icon
            AppIcon(
                imageVector = Icons.Default.Info, // or use a custom drawable
                modifier = Modifier.size(64.dp),
                tintColor = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Message for empty state
            Text(
                text = "No Tasks Available",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Add new tasks to get started!",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun FloatingButton(
    onClick: onClick = {}
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

@Composable
private fun TaskItemView(
    modifier: Modifier = Modifier,
    task: ToDoTaskEntity,
    onEvent: onHomeEvent
) {
    val textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None

    Row(
        modifier = modifier
            .clickable {
                onEvent.invoke(HomeScreenEvent.OnTaskClick(task.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { isCompleted ->
                onEvent.invoke(HomeScreenEvent.OnCheckedChange(task.copy(isCompleted = isCompleted)))
            },
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, false),
            text = task.title,
            style = MaterialTheme.typography.labelLarge.copy(
                textDecoration = textDecoration,
            ),
            color = if (task.isCompleted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        AppIconButton(
            imageVector = Icons.Default.Delete,
            onClick = {
                onEvent.invoke(HomeScreenEvent.OnDeleteTask(task))
            },
            tintColor = MaterialTheme.colorScheme.error,
        )
    }
}

