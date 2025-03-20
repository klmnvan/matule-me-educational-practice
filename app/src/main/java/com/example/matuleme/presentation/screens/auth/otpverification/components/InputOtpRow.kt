package com.example.matuleme.presentation.screens.auth.otpverification.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme.presentation.ui.theme.background
import com.example.matuleme.presentation.ui.theme.red
import com.example.matuleme.presentation.ui.theme.text

@Composable
fun InputOtpRow(code: Array<String>, onValueChanged: (Array<String>) -> Unit, onCodeIsFull: () -> Unit) {

    val codeLength = 6
    val focusRequesters = List(codeLength) { FocusRequester() }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        for (i in 0 until codeLength) {
            InputCell(code[i], focusRequesters[i]) { newValue ->
                if (newValue.length <= 1) {
                    code[i] = newValue
                    onValueChanged(code)
                    if (newValue.isNotEmpty() && i < codeLength - 1) {
                        focusRequesters[i + 1].requestFocus()
                    } else if (newValue.isEmpty() && i > 0) {
                        focusRequesters[i - 1].requestFocus()
                    }
                }
                if (code.all { it.isNotEmpty() }) {
                    onCodeIsFull()
                }
            }
        }
    }

}

@Composable
fun InputCell(value: String, focusRequester: FocusRequester, onValue: (String) -> Unit) {
    var border = Color.Transparent
    if(value == "") border = red
    OutlinedTextField(
        value = value,
        onValueChange = { onValue(it)},
        singleLine = true,
        maxLines = 1, shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(46.dp)
            .height(99.dp)
            .focusRequester(focusRequester),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = border,
            unfocusedContainerColor = background,
            unfocusedTextColor = text,
            focusedContainerColor = background,
            focusedTextColor = text,
            focusedBorderColor = border
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = text
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}