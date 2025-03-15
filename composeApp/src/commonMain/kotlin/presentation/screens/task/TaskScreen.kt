package presentation.screens.task

import ab.todoapp.shared.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.Constant.INVALID_TASK_ID
import common.onClick
import common.onColorSelected
import domain.model.ColorType
import domain.model.ColorType.Companion.toComposeColor
import presentation.screens.task.TaskScreenContract.*
import presentation.components.AppIcon
import presentation.components.AppIconButton
import presentation.extensions.collectWithLifecycle
import presentation.theme.ErrorDark
import kotlin.random.Random

data class TaskScreen(val id: Long = INVALID_TASK_ID) : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        viewModel.uiEffect.collectWithLifecycle { sideEffect ->
            when (sideEffect) {
                is TaskScreenSideEffect.NavigateToBack -> {
                    navigator.pop()
                }
            }
        }
        Scaffold(
            topBar = {
                TopBar(
                    id = id,
                    onClick = {
                        navigator.pop()
                    },
                    onDeleteClick = {
                        viewModel.onAction(TaskScreenEvent.OnDeleteTask(id))
                    }
                )
            }
        ) { paddingValues ->
            TaskView(
                modifier = Modifier.padding(paddingValues),
                state = uiState,
                onTaskEvent = viewModel::onAction,
                taskId = id,
            )
        }
        LaunchedEffect(Unit) {
            if (id != INVALID_TASK_ID) {
                viewModel.initTask(id)
            }
        }
    }
}

@Composable
fun TaskView(
    modifier: Modifier,
    state: TaskScreenUiState,
    onTaskEvent: (TaskScreenEvent) -> Unit,
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
        TextFieldColumn(
            title = state.title,
            description = state.description,
            onTitleChange = { onTaskEvent.invoke(TaskScreenEvent.UpdateTitle(it)) },
            onDescriptionChange = { onTaskEvent.invoke(TaskScreenEvent.UpdateDescription(it)) }
        )

        ColorRow(
            colors = state.colors,
            selectedColor = state.selectedColor,
            onColorSelected = {
                onTaskEvent.invoke(TaskScreenEvent.UpdateTaskColor(it))
            }
        )

        CheckBoxRow(
            isCompleted = state.isCompleted,
            onCompletedStatusChange = {
                onTaskEvent.invoke(TaskScreenEvent.UpdateCompletedStatus(it))
            }
        )

        Button(
            onClick = {
                onTaskEvent.invoke(TaskScreenEvent.SaveTaskScreen(taskId))
            },
            modifier = Modifier.align(Alignment.End),
            enabled = isButtonEnabled
        ) {
            Text(
                text = Resources.String.save_task(),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun TextFieldColumn(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        label = {
            Text(
                text = Resources.String.title(),
                style = MaterialTheme.typography.labelLarge
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        textStyle = MaterialTheme.typography.labelLarge,
    )
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = {
            Text(
                text = Resources.String.description(),
                style = MaterialTheme.typography.labelLarge
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.labelLarge,
        placeholder = {
            Text(
                text = "Enter task description",
                style = MaterialTheme.typography.labelLarge,
            )
        },
    )
}

@Composable
fun CheckBoxRow(
    isCompleted: Boolean,
    onCompletedStatusChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onCompletedStatusChange(!isCompleted)
            }
            .padding(bottom = 16.dp)
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = onCompletedStatusChange
        )
        Text(
            text = Resources.String.completed(),
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    id: Long,
    onClick: onClick,
    onDeleteClick: onClick,
) {
    TopAppBar(
        title = {
            Text(id.getStringResByTaskId())
        },
        navigationIcon = {
            AppIconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = onClick,
                tintColor = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            if (id != INVALID_TASK_ID) {
                AppIconButton(
                    imageVector = Icons.Filled.Delete,
                    onClick = onDeleteClick,
                    tintColor = ErrorDark
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun ColorRow(
    colors: List<ColorType>,
    selectedColor: ColorType?,
    onColorSelected: onColorSelected,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(colors) { color ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color.toComposeColor())
                    .border(
                        width = 1.dp,
                        color = if (color == selectedColor) Color.Black else Color.Gray,
                        shape = CircleShape
                    )
                    .clickable { onColorSelected(color) }
            ) {
                if (color == selectedColor) {
                    AppIcon(
                        imageVector = Icons.Default.Check,
                        tintColor = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Long?.getStringResByTaskId(): String {
    return if (this == INVALID_TASK_ID) {
        Resources.String.add_new_task()
    } else {
        Resources.String.update_task()
    }
}
