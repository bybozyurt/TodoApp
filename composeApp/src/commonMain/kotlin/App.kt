import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import di.appModule
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

import presentation.home.HomeScreen

@Composable
@Preview
fun App() {
    initializeKoin()
    MaterialTheme {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}