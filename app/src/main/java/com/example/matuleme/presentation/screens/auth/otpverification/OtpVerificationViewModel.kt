package com.example.matuleme.presentation.screens.auth.otpverification

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.OtpVerificationSt
import com.example.matuleme.data.states.SignInSt
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(OtpVerificationSt())
    val state: StateFlow<OtpVerificationSt> = _state.asStateFlow()

    var stateValue: OtpVerificationSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun checkOtpCode(email: String, controller: NavHostController) {
        viewModelScope.launch {
            try {
                Log.d("код", stateValue.code.joinToString("") + " ${email}")
                Constants.supabase.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = email,
                    token = stateValue.code.joinToString("")
                )
                controller.navigate(NavigationRoutes.NEWPASSWORD + "/${email}")
                Log.d("проверка кода", "Всё хорошо")
            } catch (e: Exception) {
                stateValue = stateValue.copy(
                    code = mutableListOf("", "", "", "", "", ""),
                    codeIsNotValid = true,
                    dialogIsOpen = true,
                    error = e.message.toString()
                )
            }
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            while (stateValue.timer > 0) {
                delay(1000)
                stateValue = stateValue.copy(timer = stateValue.timer - 1)
            }
        }
    }

    //отправка otp если забыл пароль
    fun sendOtpAgain(email: String) {
        viewModelScope.launch {
            try {
                Constants.supabase.auth.resetPasswordForEmail(email = email)
                startTimer()
                Log.d("отправка кода ещё раз", "ок")
            } catch (e: Exception) {
                stateValue = stateValue.copy(
                    dialogIsOpen = true,
                    error = e.message.toString()
                )
            }
        }
    }

}

