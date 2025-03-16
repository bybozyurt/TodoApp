import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ab.todoapp.ui.theme.DarkColorScheme
import ab.todoapp.ui.theme.LightColorScheme
import ab.todoapp.ui.theme.Typography

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}