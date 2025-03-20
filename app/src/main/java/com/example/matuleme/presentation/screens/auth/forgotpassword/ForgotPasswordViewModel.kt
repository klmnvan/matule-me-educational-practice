package com.example.matuleme.presentation.screens.auth.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.ForgotPasswordSt
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.validation.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordSt())
    val state: StateFlow<ForgotPasswordSt> = _state.asStateFlow()

    var stateValue: ForgotPasswordSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun sendCode() {
        viewModelScope.launch {
            try {
                if (stateValue.email.isEmailValid()) {
                    Constants.supabase.auth.resetPasswordForEmail(
                        email = stateValue.email
                    )
                    stateValue = stateValue.copy(dialogSendCodeIsOpen = true)
                }
                else stateValue = stateValue.copy(dialogErrorIsOpen = true, error = "Неверный формат почты!")
            } catch (e: Exception) {
                stateValue = stateValue.copy(dialogErrorIsOpen = true, error = e.message.toString())
            }
        }
    }

}