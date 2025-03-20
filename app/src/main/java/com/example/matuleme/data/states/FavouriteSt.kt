package com.example.matuleme.data.states

import com.example.matuleme.domain.models.Product

data class FavouriteSt(
    var products: MutableList<Product> = mutableListOf(),
    var idFavSneakers: MutableList<String> = mutableListOf(),
    var listBucket: MutableList<String> = mutableListOf(),
    val error: String = "",
    val dialogIsOpen: Boolean = false,
)

