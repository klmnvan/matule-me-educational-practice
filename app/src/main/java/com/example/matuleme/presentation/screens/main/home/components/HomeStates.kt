package com.example.matuleme.presentation.screens.main.home.components

sealed class HomeStates(title: String) {
    data object Main : HomeStates("Главное")
    data object Category : HomeStates("Категории")
}