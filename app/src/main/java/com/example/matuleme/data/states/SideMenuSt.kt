package com.example.matuleme.data.states

import com.example.matuleme.domain.models.ProfileEnt

data class SideMenuSt(
    val profile: ProfileEnt = ProfileEnt("", "", "", "", "", "", "", ""),
    val error: String = "",
    val dialogIsOpen: Boolean = false,
)

