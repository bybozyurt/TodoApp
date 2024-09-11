package presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.onClick
import common.onHomeEvent
import domain.model.ToDoTaskEntity
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)
                ) {
                    if (!tasks.isNullOrEmpty()) {
                        items(
                            items = tasks!!,
                            key = { it.id }
                        ) { task ->
                            TaskItemView(
                                modifier = Modifier
                                    .animateItemPlacement(),
                                task = task,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
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
            .background(Color.Red, CircleShape),
        imageVector = Icons.Filled.Add,
        onClick = onClick,
        tintColor = Color.Green,
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
        modifier = modifier,
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
            style = LocalTextStyle.current.copy(
                textDecoration = textDecoration,
                fontSize = 16.sp
            ),
            color = if (task.isCompleted) Color.Gray else Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        val deleteIcon = Icons.Filled.Delete
        AppIconButton(
            imageVector = Icons.Default.Delete,
            onClick = {
                onEvent.invoke(HomeScreenEvent.OnDeleteTask(task))
            },
            tintColor = Color.Red,
        )
    }
}

