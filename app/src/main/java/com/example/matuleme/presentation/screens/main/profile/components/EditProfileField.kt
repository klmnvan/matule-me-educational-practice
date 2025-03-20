package com.example.matuleme.presentation.screens.main.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.matuleme.R
import com.example.matuleme.presentation.components.spacers.SpacerHeight
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun EditProfileField(title: String, value: String, onValueChange: (String) -> Unit) {
    Text(title)
    SpacerHeight(15.dp)
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(1f),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = text,
            unfocusedContainerColor = background,
            focusedContainerColor = background,
            focusedTextColor = text,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = background
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_tick),
                contentDescription = "",
                modifier = Modifier.size(12.dp),
                tint = accent
            )
        }
    )
}