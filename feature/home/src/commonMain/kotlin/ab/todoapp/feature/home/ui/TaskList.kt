package ab.todoapp.feature.home.ui

import ab.todoapp.domain.model.ToDoTask
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ab.todoapp.feature.home.HomeScreenContract.*
import ab.todoapp.feature.home.HomeViewModel
import ab.todoapp.shared.Resources
import ab.todoapp.ui.extensions.toComposeColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: List<ToDoTask>?,
    viewModel: HomeViewModel,
    onNavigateToTaskScreen: (Long) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalItemSpacing = 12.dp,
        contentPadding = PaddingValues(16.dp)
    ) {
        item(
            span = StaggeredGridItemSpan.FullLine
        ) {
            Text(
                text = Resources.String.all_notes(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
        items(
            items = tasks!!,
            key = { it.id }
        ) { task ->
            TaskItemView(
                modifier = Modifier
                    .animateItemPlacement(),
                task = task,
                onEvent = lambda@{ event ->
                    if (event is Event.OnTaskClick) {
                        onNavigateToTaskScreen(event.id)
                        return@lambda
                    }
                    viewModel.onEvent(event)
                }
            )
        }
    }
}

@Composable
private fun TaskItemView(
    modifier: Modifier = Modifier,
    task: ToDoTask,
    onEvent: (Event) -> Unit
) {
    val textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = task.colorType.toComposeColor().copy(
                alpha = 0.8f
            )
        ),
        onClick = {
            onEvent.invoke(Event.OnTaskClick(task.id))
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textDecoration = textDecoration,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                textDecoration = textDecoration,
            )
        }
    }
}