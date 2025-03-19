package com.example.matuleme.data.states

data class SignInSt(
    val email: String = "",
    val password: String = "",
    val error: String = "",
    val dialogIsOpen: Boolean = false
)

