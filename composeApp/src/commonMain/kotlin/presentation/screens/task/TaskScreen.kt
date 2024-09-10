package presentation.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.onEvent
import kotlin.random.Random

data class TaskScreen(val id: String = "") : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<TaskViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        Scaffold { paddingValues ->
            TaskView(
                modifier = Modifier.padding(paddingValues),
                state = uiState,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )
        }
    }
}

@Composable
fun TaskView(
    modifier: Modifier,
    state: TaskScreenUiState,
    onEvent: onEvent,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title Input
        OutlinedTextField(
            value = state.title,
            onValueChange = {
                onEvent.invoke(TaskEvent.UpdateTitle(it))
            },
            label = { Text(text = "Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Description Input
        OutlinedTextField(
            value = state.description,
            onValueChange = {
                onEvent.invoke(TaskEvent.UpdateDescription(it))
            },
            label = { Text(text = "Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Completed Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Checkbox(
                checked = state.isCompleted,
                onCheckedChange = {
                    onEvent.invoke(TaskEvent.UpdateCompletedStatus(it))
                }
            )
            Text(
                text = "Completed",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // Save Button
        Button(
            onClick = {
                onEvent.invoke(TaskEvent.SaveTask)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Save Task")
        }
    }
}
