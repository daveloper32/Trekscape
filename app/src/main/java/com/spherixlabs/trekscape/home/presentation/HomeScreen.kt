@file:OptIn(ExperimentalPermissionsApi::class)

package com.spherixlabs.trekscape.home.presentation

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.spherixlabs.trekscape.core.domain.storage.model.permissions.GrantPermissionData
import com.spherixlabs.trekscape.core.presentation.components.ObserveAsEvents
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeDialog
import com.spherixlabs.trekscape.core.presentation.components.TrekScapeMagicLoadingDialog
import com.spherixlabs.trekscape.core.presentation.components.handlers.AutoFinishBackPressHandler
import com.spherixlabs.trekscape.core.presentation.ui.theme.TrekScapeTheme
import com.spherixlabs.trekscape.core.utils.context.findActivity
import com.spherixlabs.trekscape.core.utils.intent.IntentUtils
import com.spherixlabs.trekscape.core.utils.maps.MapsUtils
import com.spherixlabs.trekscape.home.domain.enums.HomeType
import com.spherixlabs.trekscape.home.presentation.components.bottom_bar.HomeBottomBar
import com.spherixlabs.trekscape.home.presentation.components.dialogs.LocationPreferencesDialog
import com.spherixlabs.trekscape.home.presentation.components.dialogs.RequestLocationPermissionDialog
import kotlinx.coroutines.launch

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
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
    val cameraPositionState = rememberCameraPositionState {
        position = MapsUtils.fromCoordinatesDataToCameraPosition(viewModel.state.currentMapCameraLocation)
    }
    ObserveAsEvents(flow = viewModel.events) { event ->
        when (event) {
            HomeEvent.RequestLocationPermissions -> {
                generalLocationPermissions.launchMultiplePermissionRequest()
            }
            HomeEvent.NavigateToAppPermissionSettings -> {
                IntentUtils.goToAppDetailsSettings(context)
            }
            is HomeEvent.UpdateMapCamera -> {
                coroutineScope.launch {
                    cameraPositionState.animate(
                        MapsUtils.fromCoordinatesDataToCameraUpdate(event.coordinates),
                    )
                }
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
        onAction = viewModel::onAction,
        cameraPositionState = cameraPositionState,
    )
}

@Composable
fun HomeScreen(
    state               : HomeState,
    onAction            : (HomeAction) -> Unit,
    cameraPositionState : CameraPositionState = rememberCameraPositionState(),
) {
    AutoFinishBackPressHandler()

    var navigationSelectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            HomeBottomBar(
                currentIndexItemSelected = navigationSelectedItem,
                onItemClicked            = { index, itemData ->
                    navigationSelectedItem = index
                    when (itemData.type) {
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
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    properties          = MapProperties(
                        isMyLocationEnabled = false,//state.isMyLocationEnabled,
                        mapType             = MapType.HYBRID,
                        isTrafficEnabled    = false,
                    ),
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = false,
                    ),
                ) {
                    state.placeRecommendations.forEach { place ->
                        Marker(
                            state = rememberMarkerState(
                                position = MapsUtils.fromCoordinatesDataToLatLng(place.location),
                            ),
                            title = place.name,
                            onClick = {
                                onAction(HomeAction.OnSomePlaceRecommendationClicked(place))
                                false
                            }
                        )
                    }
                }
            }
            RequestLocationPermissionDialog(
                isOpen     = state.isLocationPermissionBeingRequested,
                onDismiss  = { onAction(HomeAction.OnNotGrantLocationPermissions) },
                onYesClick = { onAction(HomeAction.OnGrantLocationPermissions) },
                onNoClick  = { onAction(HomeAction.OnNotGrantLocationPermissions) }
            )
            TrekScapeDialog(
                isOpen = state.isShowingHistory,
                onDismiss = { onAction(HomeAction.OnDismissHistory) }
            ) {
                Text(
                    modifier = Modifier
                        .padding(60.dp),
                    text = "History"
                )
            }
            LocationPreferencesDialog(
                isOpen = state.isLocationPreferencesBeingRequested,
                isDonTAskAgainChecked = state.isDonTAskAgainLocationPreferencesSelected,
                onDismiss = { onAction(HomeAction.OnDismissLocationPreferences) },
                onDonTAskAgainClick = { isChecked ->
                    onAction(HomeAction.OnDonTAskAgainLocationPreferencesClicked)
                },
                onOkClick = { locationPreference ->
                    onAction(HomeAction.OnLocationPreferencesSetupFilled(locationPreference))
                },
            )
            TrekScapeDialog(
                isOpen = state.isShowingProfile,
                onDismiss = { onAction(HomeAction.OnDismissProfile) }
            ) {
                Text(
                    modifier = Modifier
                        .padding(60.dp),
                    text = "Profile"
                )
            }
            TrekScapeMagicLoadingDialog(
                isOpen = state.isLoadingRecommendations,
                onDismiss = { /*Nothing*/ }
            )
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