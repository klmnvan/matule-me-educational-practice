package com.example.matuleme.presentation.screens.auth.signup

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.data.states.SignUpState
import com.example.matuleme.domain.models.ProfileEnt
import com.example.matuleme.domain.network.Constants
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    var stateValue: SignUpState
        get() = _state.value
        set(value) {
            _state.value = value
        }

    fun signUp(controller: NavHostController) {
        viewModelScope.launch {
            try {
                if(stateValue.name != "" && stateValue.email != "" && stateValue.password != "") {
                    if(stateValue.checkbox) {
                        Constants.supabase.auth.signUpWith(Email) {
                            email = stateValue.email
                            password = stateValue.password
                        }
                        Log.d("sign up", "success")
                        val currentUser = Constants.supabase.auth.currentUserOrNull()
                        if (currentUser != null) {
                            Constants.supabase.from("profiles").insert(
                                ProfileEnt(
                                    id = UUID.randomUUID().toString(),
                                    user_id = currentUser.id,
                                    firstname = stateValue.name,
                                    created_at = Timestamp(System.currentTimeMillis()).toString(),
                                    address = "",
                                    lastname = "",
                                    photo = "",
                                    phone = ""
                                )
                            )
                            Log.d("create user", "супер")
                            CacheRepository.act = 2
                            controller.navigate(NavigationRoutes.SIGNIN) {
                                popUpTo(NavigationRoutes.SIGNUP) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    else stateValue = stateValue.copy(dialogIsOpen = true, error = "Вы не дали согласие на обработку персональных данных!")
                }
                else stateValue = stateValue.copy(dialogIsOpen = true, error = "Не все поля заполнены!")
            }
            catch (e: Exception) {
                stateValue = stateValue.copy(dialogIsOpen = true, error = "${e.message}")
            }
        }
    }

}