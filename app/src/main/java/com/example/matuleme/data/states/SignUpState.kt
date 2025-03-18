package com.example.matuleme.data.states

data class SignUpState(
    val name: String = "kakashke",
    val email: String = "nesklmnvan@gmail.com",
    val password: String = "123456",
    val checkbox: Boolean = false,
    val dialogIsOpen: Boolean = false,
    val error: String = ""
)