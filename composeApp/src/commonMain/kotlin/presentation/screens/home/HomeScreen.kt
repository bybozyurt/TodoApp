package presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import common.onClick
import presentation.components.AppIconButton
import presentation.screens.task.TaskScreen
import kotlin.random.Random

class HomeScreen : Screen {

    override val key: ScreenKey =
        super.key + "${Random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE)}"

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<HomeViewModel>()

        Scaffold(
            floatingActionButton = {
                FloatingButton(
                    onClick = {
                        navigator.push(TaskScreen())
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    modifier = Modifier
                        .clickable {
                            navigator.push(TaskScreen())
                        },
                    text = "Home Screen",
                    color = Color.Green
                )
            }
        }
    }
}

@Composable
private fun FloatingButton(
    onClick: onClick = {}
) {
    AppIconButton(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(Color.Red, CircleShape),
        imageVector = Icons.Filled.Add,
        onClick = onClick,
    )
}
