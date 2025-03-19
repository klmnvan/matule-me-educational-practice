package com.example.matuleme.data.states

data class ForgotPasswordSt(
    val email: String = "nesklmnvan@gmail.com",
    val dialogErrorIsOpen: Boolean = false,
    val dialogSendCodeIsOpen: Boolean = false,
    val error: String = ""
)