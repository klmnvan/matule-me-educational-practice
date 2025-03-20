package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    var id: String,
    var title: String
)


