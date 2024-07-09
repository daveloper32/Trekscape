package com.spherixlabs.trekscape.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.spherixlabs.trekscape.core.presentation.ui.navigation.NavigationRoot
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingIfUserBasicInfoIsAlreadyFilled
            }
        }
        enableEdgeToEdge()
        setContent {
            TrekScapeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
//                        .systemBarsPadding()
                        .imePadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    if (!viewModel.state.isCheckingIfUserBasicInfoIsAlreadyFilled) {
                        NavigationRoot(
                            navController     = navController,
                            isBasicInfoFilled = viewModel.state.isBasicInfoFilled,
                        )
                    }
                }
            }
        }
    }
}