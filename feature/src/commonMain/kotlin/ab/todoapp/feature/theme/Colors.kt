package ab.todoapp.feature.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// LIGHT
val PrimaryLight = Color(0xff000000)
val OnPrimaryLight = Color(0xffffffff)
val PrimaryContainerLight = Color(0xffe0e0e0)
val OnPrimaryContainerLight = Color(0xff000000)

val SecondaryLight = Color(0xff333333)
val OnSecondaryLight = Color(0xffffffff)
val SecondaryContainerLight = Color(0xffe0e0e0)
val OnSecondaryContainerLight = Color(0xff000000)

val TertiaryLight = Color(0xff666666)
val OnTertiaryLight = Color(0xffffffff)
val TertiaryContainerLight = Color(0xfff5f5f5)
val OnTertiaryContainerLight = Color(0xff000000)

val ErrorLight = Color(0xffD32F2F)
val OnErrorLight = Color(0xffffffff)
val ErrorContainerLight = Color(0xffFFCDD2)
val OnErrorContainerLight = Color(0xffD32F2F)

val BackgroundLight = Color(0xffffffff)
val OnBackgroundLight = Color(0xff000000)
val SurfaceLight = Color(0xfff5f5f5)
val OnSurfaceLight = Color(0xff000000)

val OutlineLight = Color(0xff8c8c8c)


// DARK
val PrimaryDark = Color(0xffffffff)
val OnPrimaryDark = Color(0xff000000)
val PrimaryContainerDark = Color(0xff333333)
val OnPrimaryContainerDark = Color(0xffffffff)

val SecondaryDark = Color(0xff666666)
val OnSecondaryDark = Color(0xff000000)
val SecondaryContainerDark = Color(0xff444444)
val OnSecondaryContainerDark = Color(0xffffffff)

val TertiaryDark = Color(0xff999999)
val OnTertiaryDark = Color(0xff000000)
val TertiaryContainerDark = Color(0xff555555)
val OnTertiaryContainerDark = Color(0xffffffff)

val ErrorDark = Color(0xffCF6679)
val OnErrorDark = Color(0xff000000)
val ErrorContainerDark = Color(0xffB00020)
val OnErrorContainerDark = Color(0xffffffff)

val BackgroundDark = Color(0xff000000)
val OnBackgroundDark = Color(0xffffffff)
val SurfaceDark = Color(0xff121212)
val OnSurfaceDark = Color(0xffffffff)

val OutlineDark = Color(0xff999999)


val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    tertiary = TertiaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    onTertiary = OnTertiaryLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    tertiaryContainer = TertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    outline = OutlineLight,
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    onTertiary = OnTertiaryDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    tertiaryContainer = TertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    outline = OutlineDark,
)
