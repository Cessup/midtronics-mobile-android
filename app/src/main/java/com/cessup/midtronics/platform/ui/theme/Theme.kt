package com.cessup.midtronics.platform.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Brown80,
    secondary = Tan80,
    tertiary = Sand80,
    surface= White100,
    surfaceVariant= White100,
    onSurface = Black100,
    onSurfaceVariant = Black100,
    background = Black100,
    onPrimary = Blue80,
    onSecondary = BlueGrey80,
    onTertiary = Cyan80,
    onBackground = White100
)

private val LightColorScheme = lightColorScheme(
    primary = Brown40,
    secondary = Tan40,
    tertiary = Sand40,
    surface = Black100,
    surfaceVariant= Black100,
    onSurface = White100,
    onSurfaceVariant = White100,
    background = White100,
    onPrimary = Blue40,
    onSecondary = BlueGrey40,
    onTertiary = Cyan40,
    onBackground = Black100
)

@Composable
fun MidtronicsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}