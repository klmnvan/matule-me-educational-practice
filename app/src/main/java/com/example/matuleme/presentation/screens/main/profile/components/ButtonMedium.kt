package com.example.matuleme.presentation.screens.main.profile.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background

@Composable
fun ColumnScope.ButtonMedium(title: String, onclick: () -> Unit) {
    Button(
        onClick = {
            onclick()
        },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = background,
            containerColor = accent,
        ),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Text(
            text = title,
            style = MatuleMeTheme.typography.textInButton.copy(fontSize = 14.sp),
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 70.dp)
        )
    }
}