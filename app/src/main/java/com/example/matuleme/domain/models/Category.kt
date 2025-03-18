package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    var id_category: String,
    var category: String
)


