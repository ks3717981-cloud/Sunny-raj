package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CommandoDarkColorScheme = darkColorScheme(
  primary = GymRed,
  onPrimary = Color.White,
  primaryContainer = GymRedDark,
  onPrimaryContainer = Color.White,
  secondary = GymRedBright,
  onSecondary = Color.White,
  tertiary = GymGold,
  background = GymBlack,
  onBackground = GymTextPrimary,
  surface = GymDarkCharcoal,
  onSurface = GymTextPrimary,
  surfaceVariant = GymCardBg,
  onSurfaceVariant = GymTextSecondary,
  outline = GymCardBorder,
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true,
  dynamicColor: Boolean = false, // Keep consistent gym branding across all devices
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = CommandoDarkColorScheme,
    typography = Typography,
    content = content
  )
}

