package presentation.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.Constant
import common.onClick
import common.onTaskEvent
import presentation.components.AppIconButton
import kotlin.random.Random

data class TaskScreen(val id: Long = Constant.INVALID_TASK_ID) : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        Scaffold(
            topBar = {
                TopBar(id.getTitleByTask()) {
                    navigator.pop()
                }
            }
        ) { paddingValues ->
            TaskView(
                modifier = Modifier.padding(paddingValues),
                state = uiState,
                onTaskEvent = viewModel::onEvent,
                taskId = id,
            )
        }
        LaunchedEffect(uiState.isTaskAdded) {
            if (uiState.isTaskAdded) {
                navigator.pop()
                viewModel.updateIsTaskAdded(false)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.initTask(id)
        }
    }
}

@Composable
fun TaskView(
    modifier: Modifier,
    state: TaskScreenUiState,
    onTaskEvent: onTaskEvent,
    taskId: Long,
) {
    val isButtonEnabled by remember(state.title) {
        derivedStateOf {
            state.title.isNotEmpty()
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = {
                onTaskEvent.invoke(TaskScreenEvent.UpdateTitle(it))
            },
            label = { Text(text = "Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = state.description,
            onValueChange = {
                onTaskEvent.invoke(TaskScreenEvent.UpdateDescription(it))
            },
            label = { Text(text = "Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Checkbox(
                checked = state.isCompleted,
                onCheckedChange = {
                    onTaskEvent.invoke(TaskScreenEvent.UpdateCompletedStatus(it))
                }
            )
            Text(
                text = "Completed",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = {
                onTaskEvent.invoke(TaskScreenEvent.SaveTaskScreen(taskId))
            },
            modifier = Modifier.align(Alignment.End),
            enabled = isButtonEnabled
        ) {
            Text(text = "Save Task")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(title: String, onClick: onClick) {
    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            AppIconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = onClick,
                tintColor = Color.Black
            )
        }
    )
}

private fun Long?.getTitleByTask(): String {
    return if (this == Constant.INVALID_TASK_ID) {
        "Add New Task"
    } else {
        "Update Your Task"
    }
}
