package com.example.matuleme.presentation.ui.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object MatuleMeTheme {
    val typography: Typography
        @ReadOnlyComposable
        @Composable
        get() = LocalTypography.current
    val colors: ColorPalette
        @ReadOnlyComposable
        @Composable
        get() = LocalColors.current
}

@Composable
fun MatuleMeTheme(
    typography: Typography = LocalTypography.current,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColors provides baseColorPalette
    ){
        content()
    }
}