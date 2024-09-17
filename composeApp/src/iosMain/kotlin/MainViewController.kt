import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() =  // IDE may say can delete this but it's required.
    ComposeUIViewController {
        val isDarkTheme =
            UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                    UIUserInterfaceStyle.UIUserInterfaceStyleDark
        App(
            darkTheme = isDarkTheme,
            dynamicColor = false,
        )
    }
