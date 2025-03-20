package com.example.matuleme.data.states

import com.example.matuleme.domain.models.Category
import com.example.matuleme.domain.models.Product
import com.example.matuleme.presentation.screens.main.home.components.HomeStates

data class HomeSt(
    var products: MutableList<Product> = mutableListOf(),
    var idFavSneakers: MutableList<String> = mutableListOf(),
    var categories: MutableList<Category> = mutableListOf(),
    var listBucket: MutableList<String> = mutableListOf(),
    val error: String = "",
    val search: String = "",
    var selectedCategory: Category = Category("", "Все"),
    val dialogIsOpen: Boolean = false,
    var screenState: HomeStates = HomeStates.Main
)