package com.example.matuleme.presentation.screens.main.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.ui.theme.background

@Composable
fun ShowProfileField(title: String, content: String) {
    Text(title)
    SpacerHeight(15.dp)
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(background)
            .padding(top = 14.dp, bottom = 18.dp).padding(horizontal = 16.dp)
    ) {
        Text(content)
    }
}