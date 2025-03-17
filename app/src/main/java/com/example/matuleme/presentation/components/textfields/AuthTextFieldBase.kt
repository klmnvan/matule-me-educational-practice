package com.example.matuleme.presentation.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun AuthTextFieldBase(value: String, placeholder: String, testTag: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth().testTag(testTag),
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
        placeholder = {
            Text(
                placeholder,
                style = MatuleMeTheme.typography.authHintField
            )
        }
    )
}

