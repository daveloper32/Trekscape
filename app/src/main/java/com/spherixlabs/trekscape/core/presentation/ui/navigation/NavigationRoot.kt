package com.spherixlabs.trekscape.core.presentation.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.spherixlabs.trekscape.core.presentation.ui.navigation.routes.HomeRoute
import com.spherixlabs.trekscape.core.presentation.ui.navigation.routes.WelcomeRoute
import com.spherixlabs.trekscape.welcome.presentation.screens.name_request.NameRequestScreenRoot

@Composable
fun NavigationRoot(
    navController : NavHostController,
    modifier      : Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = WelcomeRoute,
        modifier = modifier,
    ) {
        welcomeGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.welcomeGraph(
    navController : NavHostController
) {
    navigation<WelcomeRoute>(
        startDestination = WelcomeRoute.NameRequest,
    ) {
        composable<WelcomeRoute.NameRequest> {
            NameRequestScreenRoot(
                onGoToSetupPreferencesClick = { /*TODO*/ }
            )
        }
    }
}

private fun NavGraphBuilder.homeGraph(
    navController : NavHostController
) {
    navigation<HomeRoute>(
        startDestination = HomeRoute.Home,
    ) {
        composable<HomeRoute.Home> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Home")
            }
        }
    }
}