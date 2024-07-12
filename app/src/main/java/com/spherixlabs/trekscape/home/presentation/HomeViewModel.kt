package com.spherixlabs.trekscape.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.PermissionsStateStorage
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.domain.storage.model.permissions.GrantPermissionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [HomeViewModel] is a core or base View Model that is responsible for handling the
 * control of the home of the application.
 * */
@HiltViewModel
class HomeViewModel @Inject constructor(
                userStorage        : UserStorage,
    private val resourceProvider   : ResourceProvider,
    private val permissionsStorage : PermissionsStateStorage,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [HomeState] of the view model.
     * */
    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(
            userName = userStorage.name
        )
    }

    /**
     * Private mutable [Channel] that exposes the current events [HomeEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<HomeEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [HomeEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()

    /**
     * This function receives all the possible actions [HomeAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [HomeAction].
     * */
    fun onAction(
        action : HomeAction
    ) {
        when (action) {
            HomeAction.OnScreenStarted -> checkPermissionsGrantedState()
            HomeAction.OnGrantLocationPermissions -> handleGrantLocationPermissions()
            HomeAction.OnNotGrantLocationPermissions -> handleNotGrantLocationPermissions()
            is HomeAction.OnLocationPermissionsResult -> handleLocationPermissionsResult(action.permissionsState)
            HomeAction.OnHistoryClicked -> handleHistoryClicked()
            HomeAction.OnProfileClicked -> handleProfileClicked()
            HomeAction.OnRecommendationsClicked -> handleOnRecommendationsClicked()
            HomeAction.OnDismissHistory -> handleHistoryDismiss()
            HomeAction.OnDismissProfile -> handleProfileDismiss()
        }
    }

    /**
     * This function checks if the location permissions are not granted and if so, and the rationale
     * is not shown, it try to requests the permissions.
     * */
    private fun checkPermissionsGrantedState() {
        try {
            state = state.copy(
                isMyLocationEnabled = resourceProvider.isAllLocationPermissionsGranted(),
            )
            if (
                !resourceProvider.isAllLocationPermissionsGranted() &&
                !permissionsStorage.isFineLocationRationaleShown &&
                !permissionsStorage.isCoarseLocationRationaleShown
            ) {
                state = state.copy(
                    isLocationPermissionBeingRequested = true,
                )
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function requests the location permissions.
     * */
    private fun handleGrantLocationPermissions() {
        try {
            viewModelScope.launch {
                state = state.copy(
                    isLocationPermissionBeingRequested = false,
                )
                eventChannel.send(HomeEvent.RequestLocationPermissions)
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the case when the location permissions are not granted.
     * */
    private fun handleNotGrantLocationPermissions() {
        try {
            state = state.copy(
                isLocationPermissionBeingRequested = false,
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the result of the location permissions request.
     *
     * @param permissionsState [List] <[GrantPermissionData]> that contains the current state of the
     * permissions.
     * */
    private fun handleLocationPermissionsResult(
        permissionsState : List<GrantPermissionData>
    ) {
        try {
            if (permissionsState.all { it.isGranted }) {
                state = state.copy(
                    isMyLocationEnabled = resourceProvider.isAllLocationPermissionsGranted(),
                )
            }
            permissionsState.forEach { permissionInfo ->
                when (permissionInfo.permission) {
                    android.Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        if (permissionInfo.shouldShowRationale) {
                            permissionsStorage.isCoarseLocationRationaleShown = true
                        } else {
                            if (permissionsStorage.isCoarseLocationRationaleShown) {
                                permissionsStorage.isCoarseLocationPermanentlyDeclined = true
                            }
                        }
                    }
                    android.Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (permissionInfo.shouldShowRationale) {
                            permissionsStorage.isFineLocationRationaleShown = true
                        } else {
                            if (permissionsStorage.isFineLocationRationaleShown) {
                                permissionsStorage.isFineLocationPermanentlyDeclined = true
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the history click action.
     * */
    private fun handleHistoryClicked() {
        try {
            state = state.copy(
                isShowingHistory = true,
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the recommendations click action.
     * */
    private fun handleOnRecommendationsClicked() {
        try {
            // TODO
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the profile click action.
     * */
    private fun handleProfileClicked() {
        try {
            state = state.copy(
                isShowingProfile = true,
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the history dismiss action.
     * */
    private fun handleHistoryDismiss() {
        try {
            state = state.copy(
                isShowingHistory = false,
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the profile dismiss action.
     * */
    private fun handleProfileDismiss() {
        try {
            state = state.copy(
                isShowingProfile = false,
            )
        } catch (e: Exception) { Unit }
    }
}