package com.example.matuleme.data.states

data class NewPasswordSt(
    val password: String = "1234567",
    val confirmPassword: String = "1234567",
    val error: String = "",
    val dialogIsOpen: Boolean = false
)