package org.michaelbel.cutouts

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Red10 = Color(0xFF410002)
private val Red20 = Color(0xFF690005)
private val Red30 = Color(0xFF93000A)
private val Red40 = Color(0xFFC00010)
private val Red80 = Color(0xFFFFB4AB)
private val Red90 = Color(0xFFFFDAD6)

private val RedSecondary40 = Color(0xFF775652)
private val RedSecondary80 = Color(0xFFE7BDB8)
private val RedSecondary90 = Color(0xFFFFDAD6)
private val RedSecondary20 = Color(0xFF2C1512)
private val RedSecondary30 = Color(0xFF442927)
private val RedSecondaryDark30 = Color(0xFF5D3F3C)

private val RedTertiary40 = Color(0xFF715B2E)
private val RedTertiary80 = Color(0xFFDEBC71)
private val RedTertiary90 = Color(0xFFFCDEA5)
private val RedTertiary10 = Color(0xFF261900)
private val RedTertiary20 = Color(0xFF3D2E04)
private val RedTertiaryDark30 = Color(0xFF554419)

private val Neutral10 = Color(0xFF201A19)
private val Neutral20 = Color(0xFF362F2E)
private val Neutral90 = Color(0xFFEDE0DE)
private val Neutral95 = Color(0xFFFBEEEC)
private val Neutral99 = Color(0xFFFFF8F7)

private val NeutralVariant30 = Color(0xFF534341)
private val NeutralVariant40 = Color(0xFF857370)
private val NeutralVariant50 = Color(0xFFA08C8A)
private val NeutralVariant60 = Color(0xFF534341)
private val NeutralVariant80 = Color(0xFFD8C2BF)
private val NeutralVariant90 = Color(0xFFF5DDDA)

private val LightColorScheme = lightColorScheme(
    primary = Red40,
    onPrimary = Color.White,
    primaryContainer = Red90,
    onPrimaryContainer = Red10,
    secondary = RedSecondary40,
    onSecondary = Color.White,
    secondaryContainer = RedSecondary90,
    onSecondaryContainer = RedSecondary20,
    tertiary = RedTertiary40,
    onTertiary = Color.White,
    tertiaryContainer = RedTertiary90,
    onTertiaryContainer = RedTertiary10,
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Neutral99,
    onBackground = Neutral10,
    surface = Neutral99,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    outline = NeutralVariant40,
    outlineVariant = NeutralVariant80,
    scrim = Color.Black,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    inversePrimary = Red80,
)

private val DarkColorScheme = darkColorScheme(
    primary = Red80,
    onPrimary = Red20,
    primaryContainer = Red30,
    onPrimaryContainer = Red90,
    secondary = RedSecondary80,
    onSecondary = RedSecondary30,
    secondaryContainer = RedSecondaryDark30,
    onSecondaryContainer = RedSecondary90,
    tertiary = RedTertiary80,
    onTertiary = RedTertiary20,
    tertiaryContainer = RedTertiaryDark30,
    onTertiaryContainer = RedTertiary90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Neutral10,
    onBackground = Neutral90,
    surface = Neutral10,
    onSurface = Neutral90,
    surfaceVariant = NeutralVariant30,
    onSurfaceVariant = NeutralVariant80,
    outline = NeutralVariant50,
    outlineVariant = NeutralVariant60,
    scrim = Color.Black,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    inversePrimary = Red40,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content
    )
}
