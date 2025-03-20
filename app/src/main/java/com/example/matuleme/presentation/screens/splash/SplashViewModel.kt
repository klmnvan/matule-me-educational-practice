package com.example.matuleme.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.screens.splash.components.Acts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    fun launch(controller: NavHostController) {
        viewModelScope.launch {
            delay(3000)
            if(CacheRepository.act == Acts.SIGNIN) {
                controller.navigate(NavigationRoutes.SIGNIN) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
            if(CacheRepository.act == Acts.HOME) {
                controller.navigate(NavigationRoutes.HOME) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
        }
    }

}