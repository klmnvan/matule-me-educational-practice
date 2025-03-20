package com.example.matuleme.data.states

import androidx.compose.ui.focus.FocusRequester

data class OtpVerificationSt(
    var code: MutableList<String> = mutableListOf("", "", "", "", "", ""),
    val error: String = "",
    val dialogIsOpen: Boolean = false,
    var timer: Int = 60,
    val codeIsNotValid: Boolean = false
)

