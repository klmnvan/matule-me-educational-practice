package com.example.matuleme.presentation.screens.auth.newpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.ForgotPasswordSt
import com.example.matuleme.data.states.NewPasswordSt
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(NewPasswordSt())
    val state: StateFlow<NewPasswordSt> = _state.asStateFlow()

    var stateValue: NewPasswordSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun updatePassword(controller: NavHostController) {
        viewModelScope.launch {
            try {
                if(stateValue.password.isNotEmpty() && stateValue.confirmPassword.isNotEmpty()) {
                    if(stateValue.password == stateValue.confirmPassword) {
                        Constants.supabase.auth.updateUser {
                            password = stateValue.password
                        }
                        controller.navigate(NavigationRoutes.SIGNIN) {
                            popUpTo(NavigationRoutes.SIGNIN) {
                                inclusive = true
                            }
                        }
                    }
                    else stateValue = stateValue.copy(dialogIsOpen = true, error = "Пароли не совпадают")
                }
                else stateValue = stateValue.copy(dialogIsOpen = true, error = "Не все поля заполнены")
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = e.message.toString())
            }
        }
    }

}