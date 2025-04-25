package com.example.carnetmascotas.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Colores verdes sutiles
val VerdeClaro = Color(0xFFB2DFDB)
val VerdeMedio = Color(0xFF4CAF50)
val VerdeOscuro = Color(0xFF2E7D32)
val VerdeGris = Color(0xFF81C784)
val VerdeSuave = Color(0xFFC8E6C9)
val Blanco = Color(0xFFFFFFFF)
val NegroSuave = Color(0xFF1B1B1B)

private val DarkColorScheme = darkColorScheme(
    primary = VerdeOscuro,
    secondary = VerdeGris,
    tertiary = VerdeMedio,
    background = NegroSuave,
    surface = VerdeOscuro,
    onPrimary = Blanco,
    onSecondary = Blanco,
    onTertiary = Blanco,
    onBackground = Blanco,
    onSurface = Blanco
)

private val LightColorScheme = lightColorScheme(
    primary = VerdeMedio,
    secondary = VerdeClaro,
    tertiary = VerdeGris,
    background = VerdeSuave,
    surface = Blanco,
    onPrimary = Blanco,
    onSecondary = VerdeOscuro,
    onTertiary = VerdeOscuro,
    onBackground = VerdeOscuro,
    onSurface = VerdeOscuro
)

@Composable
fun CarnetMascotasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
