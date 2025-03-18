package com.example.matuleme.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    var id_notification: String,
    var title: String,
    var discription: String,
    var date: String,
    var id_user: String
)