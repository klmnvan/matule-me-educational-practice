package com.example.matuleme.presentation.screens.main.profile.components

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.oned.Code128Writer

fun generateBarcodeBitmap(text: String, width: Int, height: Int): Bitmap? {
    val writer = Code128Writer() // Используем Code128Writer для CODE_128
    return try {
        val hints = mutableMapOf<EncodeHintType, Any>()
        hints[EncodeHintType.MARGIN] = 1 // Устанавливаем отступы

        val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height, hints)
        val barcodeWidth = bitMatrix.width
        val barcodeHeight = bitMatrix.height
        val barcodeBitmap = Bitmap.createBitmap(barcodeWidth, barcodeHeight, Bitmap.Config.RGB_565)

        for (x in 0 until barcodeWidth) {
            for (y in 0 until barcodeHeight) {
                barcodeBitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }

        barcodeBitmap
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    }
}