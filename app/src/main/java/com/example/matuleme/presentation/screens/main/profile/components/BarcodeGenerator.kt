package com.example.matuleme.presentation.screens.main.profile.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun BarcodeGenerator(text: String) {
    var barcodeBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val barcodeHeight = 100.dp
    LaunchedEffect(text) {
        barcodeBitmap = generateBarcodeBitmap(text, screenWidth, barcodeHeight.value.toInt())
    }

    barcodeBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Barcode",
            modifier = Modifier
                .fillMaxWidth() // Занимает всю ширину экрана
                .height(barcodeHeight) // Фиксированная высота
        )
    }
}