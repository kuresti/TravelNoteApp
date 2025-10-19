package com.example.travelnoteapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/********************************
 * Light Color Scheme
 * AI Assistance: Gemini on 2025-10-15
 * ASked: "For a color scheme in Android Studio using JetPack
 * what is surface?" I wanted to learn how
 * to assign colors to elements. This led me
 * to m3.material.io/styles/colors/roles to write
 * my own assignments of the color schemes
 ********************************/
private val LightColorScheme = lightColorScheme(
    primary = YlnMnBlue,
    onPrimary = White,
    primaryContainer = AirSuperiorityBlue,
    onPrimaryContainer = Black,
    secondary = UranianBlue,
    onSecondary = Black,
    background = AntiFlashWhite,
    onBackground = Black,
    surface = AntiFlashWhite,
    onSurface = Black,
    surfaceVariant = BattleshipGray,
    onSurfaceVariant = Black
)

/********************************
 * Dark Colors Scheme
 ********************************/
private val DarkColorScheme = darkColorScheme(
    primary = GoldenYellow,
    onPrimary = Black,
    primaryContainer = BurntOrange,
    onPrimaryContainer = Black,
    secondary = MutedCoral,
    onSecondary = Black,
    background = SoftBlack,
    onBackground = WarmBeige,
    surfaceVariant = NeutralShade,
    onSurfaceVariant = WarmBeige
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun TravelNoteAppTheme(
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