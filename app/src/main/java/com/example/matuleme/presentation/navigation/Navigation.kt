package com.example.matuleme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.matuleme.presentation.screens.auth.forgotpassword.ForgotPassword
import com.example.matuleme.presentation.screens.auth.newpassword.NewPassword
import com.example.matuleme.presentation.screens.auth.otpverification.OtpVerification
import com.example.matuleme.presentation.screens.auth.signin.SignIn
import com.example.matuleme.presentation.screens.auth.signup.SignUp
import com.example.matuleme.presentation.screens.main.bucket.Bucket
import com.example.matuleme.presentation.screens.main.favorites.Favourite
import com.example.matuleme.presentation.screens.main.home.Home
import com.example.matuleme.presentation.screens.main.notification.Notification
import com.example.matuleme.presentation.screens.main.profile.show.ShowProfile
import com.example.matuleme.presentation.screens.main.sidemenu.SideMenu
import com.example.matuleme.presentation.screens.splash.Splash

@Composable
fun Navigation(controller: NavHostController, barsIsVisible: MutableState<Boolean> = mutableStateOf(false)) {
    NavHost(
        startDestination = NavigationRoutes.SPLASH,
        navController = controller
    ) {
        composable(NavigationRoutes.SPLASH) {
            barsIsVisible.value = false
            Splash(controller)
        }
        composable(NavigationRoutes.SIGNIN) {
            barsIsVisible.value = false
            SignIn(controller)
        }
        composable(NavigationRoutes.SIDEMENU) {
            barsIsVisible.value = false
            SideMenu(controller)
        }
        composable(NavigationRoutes.HOME) {
            barsIsVisible.value = true
            Home(controller)
        }
        composable(NavigationRoutes.FAVOURITE) {
            barsIsVisible.value = true
            Favourite(controller)
        }
        composable(NavigationRoutes.BUCKET) {
            barsIsVisible.value = true
            Bucket(controller)
        }
        composable(NavigationRoutes.NOTIFICATION) {
            barsIsVisible.value = true
            Notification(controller)
        }
        composable(NavigationRoutes.BASICPROFILE) {
            barsIsVisible.value = true
            ShowProfile(controller)
        }
        composable(NavigationRoutes.OTP + "/{userEmail}") { arg ->
            barsIsVisible.value = false
            val userEmail = arg.arguments?.getString("userEmail")
            OtpVerification(controller, userEmail ?: "")
        }
        composable(NavigationRoutes.NEWPASSWORD + "/{userEmail}") { arg ->
            barsIsVisible.value = false
            val userEmail = arg.arguments?.getString("userEmail")
            NewPassword(controller, userEmail ?: "")
        }
    }
}