package com.example.matuleme.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.matuleme.domain.repository.CacheRepository
import com.example.matuleme.presentation.components.navigation.BottomBar
import com.example.matuleme.presentation.navigation.Navigation
import com.example.matuleme.presentation.ui.theme.MatuleMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleMeTheme {
                val context = LocalContext.current
                CacheRepository.init(context)
                val controller = rememberNavController()
                val barsIsVisible = remember { mutableStateOf(false) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { if(barsIsVisible.value) BottomBar(controller) }
                ) {
                    Navigation(controller, barsIsVisible)
                }
            }
        }
    }
}
