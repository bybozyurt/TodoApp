package presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

val <T> Flow<T>.collectWithLifecycle: @Composable (collector: (T) -> Unit) -> Unit
    @Composable get() = { collector ->
        CollectWithLifecycle { collector(it) }
    }

@Composable
private fun <T> Flow<T>.CollectWithLifecycle(
    lifeCycleState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(this) {
        lifecycleOwner.repeatOnLifecycle(lifeCycleState) {
            collect(collector)
        }
    }
}