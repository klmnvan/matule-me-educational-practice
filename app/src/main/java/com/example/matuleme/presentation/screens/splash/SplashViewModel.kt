package com.example.matuleme.presentation.screens.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.navigation.NavigationRoutes
import com.example.matuleme.presentation.screens.splash.components.Acts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    fun launch(controller: NavHostController, context: Context) {
        viewModelScope.launch {
            delay(3000)
            if(CacheRepository.act == Acts.ONBOARDING) {
                controller.navigate(NavigationRoutes.ONBOARDING) {
                    popUpTo(NavigationRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
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