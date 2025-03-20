package com.example.matuleme.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.R
import com.example.matuleme.presentation.ui.theme.accent
import com.example.matuleme.presentation.ui.theme.block

@Composable
fun Splash(controller: NavHostController, vm: SplashViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        vm.launch(controller)
    }

    Box(modifier = Modifier
        .background(accent)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.clip(CircleShape).background(block)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_splash),
                contentDescription = "",
                modifier = Modifier.padding(34.dp).size(60.dp),
                tint = accent
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Splash(rememberNavController())
}