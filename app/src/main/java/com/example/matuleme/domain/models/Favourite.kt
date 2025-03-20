package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Favourite(
    var id: String,
    var product_id: String,
    var user_id: String,
)