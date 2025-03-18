package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileEnt(
    var id: String,
    var created_at: String,
    var user_id: String,
    var photo: String,
    var firstname: String,
    var lastname: String,
    var address: String,
    var phone: String,
)
