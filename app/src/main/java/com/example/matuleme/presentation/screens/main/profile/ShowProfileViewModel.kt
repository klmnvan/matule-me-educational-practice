package com.example.matuleme.presentation.screens.main.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleme.data.states.HomeSt
import com.example.matuleme.data.states.ProfileSt
import com.example.matuleme.data.states.SideMenuSt
import com.example.matuleme.domain.network.Requests
import com.example.matuleme.presentation.screens.main.profile.components.ProfileStates
import com.example.matuleme.presentation.screens.main.sidemenu.SideMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProfileSt())
    val state: StateFlow<ProfileSt> = _state.asStateFlow()

    var stateValue: ProfileSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun getData() {
        viewModelScope.launch {
            try {
                val profile = Requests.getProfile()
                stateValue = stateValue.copy(profile = profile, editProfile = profile, stateScreen = ProfileStates.Show)
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("getData() | ошибка", e.message.toString())
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            try {
                Requests.updateProfile(stateValue.editProfile)
                getData()
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("getData() | ошибка", e.message.toString())
            }
        }
    }

}