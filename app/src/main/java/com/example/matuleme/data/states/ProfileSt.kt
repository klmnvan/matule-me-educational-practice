package com.example.matuleme.data.states

import com.example.matuleme.domain.models.ProfileEnt
import com.example.matuleme.presentation.screens.main.profile.components.ProfileStates

data class ProfileSt(
    val profile: ProfileEnt = ProfileEnt("", "", "", "", "", "", "", ""),
    val editProfile: ProfileEnt = ProfileEnt("", "", "", "", "", "", "", ""),
    val stateScreen: ProfileStates = ProfileStates.Show,
    val error: String = "",
    val dialogIsOpen: Boolean = false,
)