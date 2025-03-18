package com.example.matuleme.data.states

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val checkbox: Boolean = false,
)