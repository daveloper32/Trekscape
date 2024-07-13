@file:OptIn(ExperimentalPermissionsApi::class)

package com.spherixlabs.trekscape.home.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState
import com.spherixlabs.trekscape.core.domain.storage.model.permissions.GrantPermissionData
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeSheetDialog
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.context.findActivity
import com.spherixlabs.trekscape.core.utils.intent.IntentUtils
import com.spherixlabs.trekscape.historical.presentation.screens.list_history.HistoricalScreenRoot
import com.spherixlabs.trekscape.home.domain.enums.HomeType
import com.spherixlabs.trekscape.home.presentation.components.BottomBarHome
import com.spherixlabs.trekscape.home.presentation.components.TopBarHome
import com.spherixlabs.trekscape.home.presentation.components.dialogs.RequestLocationPermissionDialog

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val generalLocationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        onPermissionsResult = {
            val permissionData = mutableListOf<GrantPermissionData>()
            it.forEach { data ->
                permissionData.add(
                    GrantPermissionData(
                        isGranted = data.value,
                        permission = data.key,
                        shouldShowRationale = context.findActivity()?.shouldShowRequestPermissionRationale(data.key) ?: false,
                    )
                )
            }
            viewModel.onAction(
                HomeAction.OnLocationPermissionsResult(
                    permissionData
                )
            )
        }
    )
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.onAction(HomeAction.OnScreenStarted)
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            HomeEvent.RequestLocationPermissions -> {
                generalLocationPermissions.launchMultiplePermissionRequest()
            }
            HomeEvent.NavigateToAppPermissionSettings -> {
                IntentUtils.goToAppDetailsSettings(context)
            }
            is HomeEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    HomeScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    state    : HomeState,
    onAction : (HomeAction) -> Unit,
) {
    AutoFinishBackPressHandler()
    val cameraPositionState = rememberCameraPositionState()
    Scaffold(
        modifier  = Modifier.fillMaxSize(),
        topBar    = { TopBarHome(state.userName) },
        bottomBar = {
            BottomBarHome {itemData->
                when (itemData) {
                    HomeType.HISTORY -> {
                        onAction(HomeAction.OnHistoryClicked)
                    }
                    HomeType.RECOMMENDATIONS -> {
                        onAction(HomeAction.OnRecommendationsClicked)
                    }
                    HomeType.PROFILE -> {
                        onAction(HomeAction.OnProfileClicked)
                    }
                }
            }
        }
    ) {
        Box{
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties          = MapProperties(
                    isMyLocationEnabled = state.isMyLocationEnabled,
                    mapType             = MapType.NORMAL,
                    isTrafficEnabled    = false,
                )
            )
            RequestLocationPermissionDialog(
                isOpen     = state.isLocationPermissionBeingRequested,
                onDismiss  = { onAction(HomeAction.OnNotGrantLocationPermissions) },
                onYesClick = { onAction(HomeAction.OnGrantLocationPermissions) },
                onNoClick  = { onAction(HomeAction.OnNotGrantLocationPermissions) }
            )
            TrekScapeSheetDialog(
                isOpen = state.isShowingHistory,
                onDismiss = { onAction(HomeAction.OnDismissHistory) }
            ) {
                HistoricalScreenRoot()
            }
            TrekScapeSheetDialog(
                isOpen = state.isShowingProfile,
                onDismiss = { onAction(HomeAction.OnDismissProfile) }
            ) {
                Text(
                    modifier = Modifier
                        .padding(60.dp),
                    text = "Profile"
                )
            }
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    TrekScapeTheme {
        HomeScreen(
            state = HomeState(),
            onAction = {},
        )
    }
}