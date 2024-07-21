package com.spherixlabs.trekscape.profile.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.welcome.domain.model.PreferenceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ProfileViewModel] is a core or base View Model that is responsible for handling the
 * control of the Profile of the application.
 * */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val resourceProvider : ResourceProvider,
    private val userStorage      : UserStorage,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [ProfileState] of the view model.
     * */
    var state by mutableStateOf(ProfileState())
        private set

    /**
     * Private mutable [Channel] that exposes the current events [ProfileEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<ProfileEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [ProfileEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()

    init {
       handleStarting()
    }

    /**
     * This function receives all the possible actions [ProfileAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [ProfileAction].
     * */
    fun onAction(
        action : ProfileAction
    ) {
        when (action) {
            ProfileAction.OnScreenStarted -> handleStarting()
            ProfileAction.OnEditGeneralPreferences -> {
                viewModelScope.launch {
                    eventChannel.send(
                        ProfileEvent.GoToGeneralPreferences
                    )
                }
            }
            ProfileAction.OnEditLocationPreferences -> {
                viewModelScope.launch {
                    eventChannel.send(
                        ProfileEvent.GoToLocationPreferences
                    )
                }
            }
        }
    }

    /**
     * This function handles the starting action.
     * */
    private fun handleStarting() {
        try {
            state = state.copy(
                userName                   = userStorage.name,
                natureAdventurePreferences = userStorage.preferences
                    .filter { PreferenceModel.isNatureAdventure(it) }
                    .mapNotNull { PreferenceModel.fromString(it) },
                cultureHistoryPreferences  = userStorage.preferences
                    .filter { PreferenceModel.isCultureHistory(it) }
                    .mapNotNull { PreferenceModel.fromString(it) },
                relaxationPreferences      = userStorage.preferences
                    .filter { PreferenceModel.isRelaxation(it) }
                    .mapNotNull { PreferenceModel.fromString(it) },
                locationPreference         = userStorage.locationPreference,
            )
        } catch (e: Exception) { Unit }
    }
}