package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    var id: String,
    var title: String,
    var category_id: String,
    var cost: Float,
    var description: String,
    var is_best_seller: Boolean,
)