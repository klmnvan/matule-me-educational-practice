package com.example.matuleme.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val red = Color(0xFFF87265)
val accent = Color(0xFF48B2E7)
val disable = Color(0xFF2B6B8B)
val subtextlight = Color(0xFFD8D8D8)
val background = Color(0xFFF7F7F9)
val block = Color(0xFFFFFFFF)
val text = Color(0xFF2B2B2B)
val hint = Color(0xFF6A6A6A)
val subtextdark = Color(0xFF707B81)

data class ColorPalette(
    val background: Color,
    val primary: Color,
    val text: Color,
    val container: Color,
    val vector: Color,
)

val baseColorPalette = ColorPalette(
    background = background,
    primary = accent,
    text = text,
    container = block,
    vector = hint
)

val LocalColors = staticCompositionLocalOf { baseColorPalette }