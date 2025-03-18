package com.example.matuleme.presentation.screens.auth.signin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.SignInState
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.validation.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    var stateValue: SignInState
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun signIn(controller: NavHostController, context: Context) {
        viewModelScope.launch {
            try {
                if(stateValue.email.isNotEmpty() && stateValue.password.isNotEmpty()) {
                    if(stateValue.email.isEmailValid()) {
                        Toast.makeText(context, "Попытка входа", Toast.LENGTH_SHORT).show()
                        Constants.supabase.auth.signOut()
                        Constants.supabase.auth.signInWith(Email) {
                            email = stateValue.email
                            password = stateValue.password
                        }
                        Log.d("sign in", "успешно")
                        val currentUser = Constants.supabase.auth.currentUserOrNull()
                        Log.d("curUser", currentUser.toString())
                        if(currentUser != null) {
                            CacheRepository.uuidCurrentUser = currentUser.id
                        }
                        Log.d("uuid current user", CacheRepository.uuidCurrentUser)
                        CacheRepository.act = 2
                        controller.navigate(NavigationRoutes.HOME) {
                            popUpTo(NavigationRoutes.SIGNIN) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                if(e.message == "Invalid login credentials") {
                    Toast.makeText(context, "Неверные данные входа", Toast.LENGTH_SHORT).show()
                    Log.d("sign in error", e.message.toString())
                } else {
                    Log.d("sign in error", e.message.toString())
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}