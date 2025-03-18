package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Favourite(
    var id: String,
    var id_sneaker: String,
    var id_user: String,
)