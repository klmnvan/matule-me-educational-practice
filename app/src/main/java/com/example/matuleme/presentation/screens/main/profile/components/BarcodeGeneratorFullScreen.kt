package com.example.matuleme.presentation.screens.main.profile.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
fun BarcodeGeneratorFullScreen(text: String) {
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = (displayMetrics.widthPixels * 0.9).toInt()
    val screenHeight = (displayMetrics.heightPixels * 0.6).toInt()

    LaunchedEffect(text) {
        barcodeBitmap = generateBarcodeBitmap(text, screenWidth, screenHeight)
    }

    barcodeBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Barcode",
            modifier = Modifier
                .fillMaxSize() // Занимает всю ширину и высоту экрана
        )
    }
}