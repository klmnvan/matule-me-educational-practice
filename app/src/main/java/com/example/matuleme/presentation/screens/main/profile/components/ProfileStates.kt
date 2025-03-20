package com.example.matuleme.presentation.screens.main.profile.components

sealed class ProfileStates(title: String) {
    data object Show : ProfileStates("Show")
    data object Edit : ProfileStates("Edit")
    data object Card : ProfileStates("Loyalty Card")
}