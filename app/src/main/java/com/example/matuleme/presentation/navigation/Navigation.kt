package com.example.matuleme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme.presentation.screens.auth.signin.SignIn
import com.example.matuleme.presentation.screens.main.home.Home
import com.example.matuleme.presentation.screens.splash.Splash

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(
        startDestination = NavigationRoutes.SIGNIN,
        navController = controller
    )
    {
        composable(NavigationRoutes.SPLASH){
            Splash(controller)
        }
        composable(NavigationRoutes.SIGNIN){
            SignIn(controller)
        }
        composable(NavigationRoutes.HOME){
            Home(controller)
        }
    }
}