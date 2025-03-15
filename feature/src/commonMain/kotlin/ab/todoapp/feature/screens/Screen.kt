package ab.todoapp.feature.screens

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Screen : ScreenProvider {
    data object HomeScreen : Screen()
    data class TaskScreen(val id: String) : Screen()
}