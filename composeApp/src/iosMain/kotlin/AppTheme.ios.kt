import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ab.todoapp.feature.theme.DarkColorScheme
import ab.todoapp.feature.theme.LightColorScheme

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