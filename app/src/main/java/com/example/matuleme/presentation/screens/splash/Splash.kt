package com.example.matuleme.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme.presentation.ui.theme.background

@Composable
fun Splash(controller: NavHostController) {

    val vm = viewModel { SplashViewModel() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.launch(controller, context)
    }

    Box(modifier = Modifier
        .background(background)
        .fillMaxSize())
    {

    }

}