package presentation.components

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import common.onClick

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String = "",
    enabled: Boolean = true,
    tintColor: Color,
    onClick: onClick,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        AppIcon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tintColor = tintColor
        )
    }
}