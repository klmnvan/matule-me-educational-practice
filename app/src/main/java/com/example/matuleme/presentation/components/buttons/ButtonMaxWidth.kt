package com.example.matuleme.presentation.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background

@Composable
fun ButtonMaxWidth(title: String, onclick: () -> Unit) {
    Button(
        onClick = {
            onclick()
        },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = background,
            containerColor = accent
        ), modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text(
            text = title,
            style = MatuleMeTheme.typography.textInButton
        )
    }
}