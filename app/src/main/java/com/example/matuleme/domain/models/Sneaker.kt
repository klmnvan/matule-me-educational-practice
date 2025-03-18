package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Sneaker(
    var id_sneaker: String,
    var title: String,
    var small_description: String,
    var full_description: String,
    var image: String,
    var cost: Float,
    var id_category: String,
    var is_popular: Boolean,
    var small_title: String,
)