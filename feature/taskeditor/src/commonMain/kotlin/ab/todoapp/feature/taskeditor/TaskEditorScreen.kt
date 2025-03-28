package ab.todoapp.feature.taskeditor

import ab.todoapp.common.Constant.INVALID_TASK_ID
import ab.todoapp.domain.model.ColorType
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ab.todoapp.feature.taskeditor.TaskScreenContract.*
import ab.todoapp.ui.extensions.toComposeColor
import ab.todoapp.ui.components.AppIcon
import ab.todoapp.ui.components.AppIconButton
import ab.todoapp.ui.extensions.collectWithLifecycle
import ab.todoapp.ui.theme.ErrorDark
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TaskEditorScreen(
    viewModel: TaskEditorViewModel,
    navigateToBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.uiEffect.collectWithLifecycle { sideEffect ->
        when (sideEffect) {
            is SideEffect.NavigateToBack -> navigateToBack()
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                id = viewModel.taskId,
                onClick = navigateToBack,
                onDeleteClick = {
                    viewModel.onAction(Events.OnDeleteTask(viewModel.taskId))
                }
            )
        }
    ) { paddingValues ->
        TaskView(
            modifier = Modifier.padding(paddingValues),
            state = uiState,
            onTaskEvent = viewModel::onAction,
            taskId = viewModel.taskId,
        )
    }
}

@Composable
private fun TaskView(
    modifier: Modifier,
    state: UiState,
    onTaskEvent: (Events) -> Unit,
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
            .padding(horizontal = 16.dp)
    ) {
        TextFieldColumn(
            title = state.title,
            description = state.description,
            onTitleChange = { onTaskEvent.invoke(Events.UpdateTitle(it)) },
            onDescriptionChange = { onTaskEvent.invoke(Events.UpdateDescription(it)) }
        )

        ColorRow(
            colors = state.colors,
            selectedColor = state.selectedColor,
            onColorSelected = {
                onTaskEvent.invoke(Events.UpdateTaskColor(it))
            }
        )

        CheckBoxRow(
            isCompleted = state.isCompleted,
            onCompletedStatusChange = {
                onTaskEvent.invoke(Events.UpdateCompletedStatus(it))
            }
        )

        Button(
            onClick = {
                onTaskEvent.invoke(Events.SaveTaskScreen(taskId))
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
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
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
    onColorSelected: (ColorType) -> Unit,
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
