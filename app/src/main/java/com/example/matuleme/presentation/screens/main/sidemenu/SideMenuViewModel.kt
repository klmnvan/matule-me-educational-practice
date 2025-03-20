package com.example.matuleme.presentation.screens.main.sidemenu

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.HomeSt
import com.example.matuleme.data.states.SideMenuSt
import com.example.matuleme.domain.models.Category
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.domain.network.Constants.supabase
import com.example.matuleme.domain.network.Requests
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.storage.storage
import io.ktor.http.content.CachingProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SideMenuViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SideMenuSt())
    val state: StateFlow<SideMenuSt> = _state.asStateFlow()

    var stateValue: SideMenuSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    @SuppressLint("SuspiciousIndentation")
    fun getData() {
        viewModelScope.launch {
            try {
                val profile = Requests.getProfile()
                stateValue = stateValue.copy(profile = profile)
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("getData() | ошибка", e.message.toString())
            }
        }
    }

    fun signOut(controller: NavHostController) {
        viewModelScope.launch {
            try {
                supabase.auth.signOut()
                CacheRepository.act = 1
                controller.navigate(NavigationRoutes.SIGNIN) {
                    popUpTo(NavigationRoutes.SIDEMENU) {
                        inclusive = true
                    }
                }
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
                Log.d("getData() | ошибка", e.message.toString())
            }
        }
    }
}