package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf<AppStyles.Companion.CustomColors> {
    error("No custom colors provided")
}

@Composable
fun AppTheme(isDarkTheme: Boolean, toggleTheme: () -> Unit, content: @Composable () -> Unit) {
    val customColors = if (isDarkTheme) {
        AppStyles.getDarkColors()
    } else {
        AppStyles.getLightColors()
    }

    val typography = if (isDarkTheme) {
        AppStyles.getDarkTypography()
    } else {
        AppStyles.getLightTypography()
    }

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colors = customColors.materialColors,
            typography = typography,
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}
