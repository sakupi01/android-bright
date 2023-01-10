package com.sakura.myandroidapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
// ..
)
private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
// ..
)

@Composable
fun ReplyTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable()() - > Unit
) {
    val colorSchemecolors =
        if (!useDarkTheme) {
            LightColorsScheme
        } else {
            DarkColorsScheme
        }
    MaterialTheme(
        colorScheme = colorSchemecolors,
        content = content
    )
}