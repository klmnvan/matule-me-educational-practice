package com.example.matuleme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme.presentation.screens.auth.forgotpassword.ForgotPassword
import com.example.matuleme.presentation.screens.auth.otpverification.OtpVerification
import com.example.matuleme.presentation.screens.auth.signin.SignIn
import com.example.matuleme.presentation.screens.auth.signup.SignUp
import com.example.matuleme.presentation.screens.main.home.Home
import com.example.matuleme.presentation.screens.splash.Splash

@Composable
fun Navigation(controller: NavHostController) {
    NavHost(
        startDestination = NavigationRoutes.SIGNUP,
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
        composable(NavigationRoutes.SIGNUP){
            SignUp(controller)
        }
        composable(NavigationRoutes.FORGOTPASSWORD){
            ForgotPassword(controller)
        }
        composable(NavigationRoutes.OTP + "/{userEmail}") { arg ->
            val userEmail = arg.arguments?.getString("userEmail")
            OtpVerification(controller, userEmail ?: "")
        }
    }
}