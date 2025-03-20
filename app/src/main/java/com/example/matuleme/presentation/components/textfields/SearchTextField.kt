package com.example.matuleme.presentation.components.textfields

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.block
import com.example.matuleme.presentation.ui.theme.hint
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun RowScope.SearchTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.weight(1f),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = text,
            unfocusedContainerColor = block,
            focusedContainerColor = block,
            focusedTextColor = text,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = block
        ),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_serach),
                contentDescription = "",
                tint = hint
            )
        },
        placeholder = {
            Text(
                "Поиск",
                style = MatuleMeTheme.typography.authHintField
            )
        }
    )
}