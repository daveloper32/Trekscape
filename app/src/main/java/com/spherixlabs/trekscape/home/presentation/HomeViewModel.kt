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
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.utils.toUiText
import com.spherixlabs.trekscape.core.utils.coordinates.model.CoordinatesData
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.use_cases.GetSomePlaceRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * The [HomeViewModel] is a core or base View Model that is responsible for handling the
 * control of the home of the application.
 * */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val resourceProvider                   : ResourceProvider,
    private val permissionsStorage                 : PermissionsStateStorage,
    private val userStorage                        : UserStorage,
    private val getSomePlaceRecommendationsUseCase : GetSomePlaceRecommendationsUseCase,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [HomeState] of the view model.
     * */
    var state by mutableStateOf(HomeState())
        private set

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
            HomeAction.OnDismissLocationPreferences -> handleDismissLocationPreferences()
            HomeAction.OnDonTAskAgainLocationPreferencesClicked -> handleDonTAskAgainLocationPreferencesClicked()
            is HomeAction.OnLocationPreferencesSetupFilled -> handleLocationPreferencesSetupFilled(action.locationPreference)
            is HomeAction.OnSomePlaceRecommendationClicked -> handleSomePlaceRecommendationClicked(action.placeRecommendation)
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
            if (state.placeRecommendations.isEmpty()) {
                tryToGetAndHandleCurrentUserLocation()
            }
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
     * This function gets the current user location and updates the state with the new location.
     * */
    private fun tryToGetAndHandleCurrentUserLocation() {
        try {
            viewModelScope.launch {
                delay(1000)
                val currentLocation : CoordinatesData = resourceProvider
                    .getCurrentCoordinates()?: return@launch
                eventChannel.send(HomeEvent.UpdateMapCamera(listOf(currentLocation)))
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
                tryToGetAndHandleCurrentUserLocation()
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
            if (userStorage.isLocationPreferencesSetAsDonTAskAgain) {
                tryToGetSomeRecommendations()
            } else {
                state = state.copy(
                    isLocationPreferencesBeingRequested = true,
                    isDonTAskAgainLocationPreferencesSelected = userStorage.isLocationPreferencesSetAsDonTAskAgain,
                    currentLocationPreference = userStorage.locationPreference,
                )
            }
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

    /**
     * This function handles the location preferences dismiss action.
     * */
    private fun handleDismissLocationPreferences() {
        try {
            state = state.copy(
                isLocationPreferencesBeingRequested = false,
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the don't ask again location preferences click action.
     * */
    private fun handleDonTAskAgainLocationPreferencesClicked() {
        try {
            state = state.copy(
                isDonTAskAgainLocationPreferencesSelected = !userStorage.isLocationPreferencesSetAsDonTAskAgain,
            )
            userStorage.isLocationPreferencesSetAsDonTAskAgain = !userStorage.isLocationPreferencesSetAsDonTAskAgain
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the location preferences setup filled action.
     *
     * @param locationPreference [LocationPreference] that is selected by the user.
     * */
    private fun handleLocationPreferencesSetupFilled(
        locationPreference : LocationPreference
    ) {
        try {
            userStorage.locationPreference = locationPreference
            state = state.copy(
                isLocationPreferencesBeingRequested = false,
                currentLocationPreference = locationPreference,
            )
            tryToGetSomeRecommendations()
        } catch (e: Exception) { Unit }
    }

    /**
     * This function handles the some place recommendation click action.
     *
     * @param placeRecommendation [PlaceRecommendation] that is clicked.
     * */
    private fun handleSomePlaceRecommendationClicked(
        placeRecommendation : PlaceRecommendation
    ) {
        try {
            Timber.e("$placeRecommendation")
        } catch (e: Exception) { Unit }
    }

    /**
     * This function tries to get some place recommendations and updates the state accordingly.
     * */
    private fun tryToGetSomeRecommendations(
    ) {
        try {
            viewModelScope.launch {
                state = state.copy(
                    isLoadingRecommendations = true,
                )
                val result = getSomePlaceRecommendationsUseCase.invoke()
                state = state.copy(
                    isLoadingRecommendations = false,
                )
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            placeRecommendations = result.data
                        )
                        eventChannel.send(HomeEvent.UpdateMapCamera(result.data.map { it.location }))
                    }
                    is Result.Error -> {
                        eventChannel.send(HomeEvent.Error(result.error.toUiText()))
                    }
                }
            }
        } catch (e: Exception) { Unit }
    }
}