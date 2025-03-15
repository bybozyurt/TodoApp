package ab.todoapp.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    data object HomeScreen : SharedScreen()
    data class TaskEditorScreen(val id: Long = 0L) : SharedScreen()
}