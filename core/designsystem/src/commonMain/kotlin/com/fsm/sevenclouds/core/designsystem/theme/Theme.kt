package com.fsm.sevenclouds.core.designsystem.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

private val Purple80 = Color(0xFFD0BCFF)
private val PurpleGrey80 = Color(0xFFCCC2DC)
private val Pink80 = Color(0xFFEFB8C8)

private val Purple40 = Color(0xFF6650a4)
private val PurpleGrey40 = Color(0xFF625b71)
private val Pink40 = Color(0xFF7D5260)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    secondary = SecondaryColor,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    secondary = SecondaryColor,
    background = Color.White,
    surfaceVariant = Color.White,
    surface = lightSurface,

    tertiary = Pink40,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun SevenCloudsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
        typography = RijksmuseumTypography,
        content = content
    )
}

