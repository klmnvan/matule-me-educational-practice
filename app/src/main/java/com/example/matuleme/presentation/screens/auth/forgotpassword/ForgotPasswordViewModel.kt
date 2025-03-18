package com.example.matuleme.presentation.screens.auth.forgotpassword

import androidx.lifecycle.ViewModel
import com.example.matuleme.data.states.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    var stateValue: SignInState
        get() = _state.value
        set(value) {
            _state.value = value
        }

}