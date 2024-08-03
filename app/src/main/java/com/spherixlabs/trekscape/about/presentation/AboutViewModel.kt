package com.spherixlabs.trekscape.about.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.about.domain.model.DeveloperProfile
import com.spherixlabs.trekscape.about.domain.use_cases.GetDeveloperProfilesUseCase
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.utils.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [AboutViewModel] is a core or base View Model that is responsible for handling the
 * control of the historical of the application.
 * */
@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getDeveloperProfilesUseCase : GetDeveloperProfilesUseCase,
) : ViewModel() {
    /**
     * Private mutable [Channel] that exposes the current events [AboutEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<AboutEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [AboutEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()
    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [AboutState] of the view model.
     * */
    var state by mutableStateOf(AboutState())
        private set

    init {
        getDeveloperProfiles()
    }

    /**
     * This function receives all the possible actions [AboutAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [AboutAction].
     * */
    fun onAction(
        action : AboutAction
    ) {
        when (action) {
            is AboutAction.OnSomeDeveloperProfileClicked -> handleSomeDeveloperProfileClicked(action.developer)
        }
    }

    /**
     * This function handles the some developer profile click action.
     *
     * @param developer [DeveloperProfile] the developer profile clicked.
     * */
    private fun handleSomeDeveloperProfileClicked(
        developer : DeveloperProfile
    ) {
        try {
            viewModelScope.launch {
                eventChannel.send(AboutEvent.GoToSomeUrl(developer.profileUrl))
            }
        } catch (e: Exception) { Unit }
    }

    /**
     * This function gets the developer profiles and updates the state accordingly.
     * */
    private fun getDeveloperProfiles() {
        try {
            viewModelScope.launch {
                state = state.copy(
                    isLoadingDeveloperProfiles = true,
                )
                val result = getDeveloperProfilesUseCase()
                when (result) {
                    is Result.Success -> {
                        state = state.copy(
                            developerProfiles = result.data,
                            isLoadingDeveloperProfiles = false
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isLoadingDeveloperProfiles = false,
                        )
                        eventChannel.send(AboutEvent.Error(result.error.toUiText()))
                    }
                }
            }
        } catch (e: Exception) { Unit }
    }
}