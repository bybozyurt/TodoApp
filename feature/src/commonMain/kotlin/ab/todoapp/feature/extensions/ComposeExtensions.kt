package ab.todoapp.feature.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ColorType.BLUE
import ab.todoapp.domain.model.ColorType.GRAY
import ab.todoapp.domain.model.ColorType.GREEN
import ab.todoapp.domain.model.ColorType.ORANGE
import ab.todoapp.domain.model.ColorType.PURPLE
import ab.todoapp.domain.model.ColorType.RED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

val <T> Flow<T>.collectWithLifecycle: @Composable (collector: (T) -> Unit) -> Unit
    @Composable get() = { collector ->
        CollectSideEffect { collector(it) }
    }

@Composable
private fun <T> Flow<T>.CollectSideEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = Dispatchers.Main.immediate,
    onSideEffect: suspend CoroutineScope.(effect: T) -> Unit,
) {
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            if (context == EmptyCoroutineContext) {
                collect { onSideEffect(it) }
            } else {
                withContext(context) {
                    collect { onSideEffect(it) }
                }
            }
        }
    }
}

fun ColorType.toComposeColor(): Color {
    return when (this) {
        GREEN -> Color(0xFF388E3C)
        ORANGE -> Color(0xFFFF9800)
        RED -> Color.Red
        BLUE -> Color.Blue
        PURPLE -> Color.Magenta
        GRAY -> Color.Gray
    }
}