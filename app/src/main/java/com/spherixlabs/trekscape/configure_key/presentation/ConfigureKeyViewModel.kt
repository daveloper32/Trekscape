package com.spherixlabs.trekscape.configure_key.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_IMAGE_ON_NOT_IMAGE_PROVIDER
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.coordinates.CoordinatesUtils
import com.spherixlabs.trekscape.recommendations.domain.use_cases.GetSomePlaceRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ConfigureKeyViewModel] is a core or base View Model that is responsible for handling the
 * control of the name request of the application.
 * */
@HiltViewModel
class ConfigureKeyViewModel @Inject constructor(
    private val userStorage                        : UserStorage,
    private val getSomePlaceRecommendationsUseCase : GetSomePlaceRecommendationsUseCase,
) : ViewModel() {

    /**
     * Private [MutableStateFlow] and Public [StateFlow] that exposes the current state [ConfigureKeyState] of the view model.
     * */
    var state by mutableStateOf(ConfigureKeyState())
        private set
    /**
     * Private mutable [Channel] that exposes the current events [ConfigureEvent] launched by
     * the view model that should be consumed by the view.
     * */
    private val eventChannel = Channel<ConfigureEvent>()
    /**
     * Public mutable [Channel] as a [Flow] that exposes the current events [ConfigureEvent]
     * launched by the view model that should be consumed by the view.
     * */
    val events = eventChannel.receiveAsFlow()

    /**
     * This function initializes the state
     * */
    private fun initConfiguration(){
        try {
            state = state.copy(
                isRequestingApiKey = true,
                enableValidate = false,
                apiKey = EMPTY_STR,
            )
        } catch (e: Exception) { Unit }
    }
    /**
     * This function receives all the possible actions [ConfigureKeyAction] from the view and
     * updates the state to reflect the new action.
     *
     * @param action [ConfigureKeyAction].
     * */
    fun onAction(
        action : ConfigureKeyAction
    ) {
        when (action) {
            ConfigureKeyAction.OnScreenStarted -> initConfiguration()
            is ConfigureKeyAction.OnApiKeyChanged ->  {
                state = state.copy(
                    apiKey     = action.apikey,
                    enableValidate = action.apikey.isNotEmpty(),
                )
            }

            ConfigureKeyAction.OnSave -> {
                viewModelScope.launch {
                    userStorage.apiKey = state.apiKey
                    eventChannel.send(ConfigureEvent.GoBack)
                }
            }

            ConfigureKeyAction.OnBack -> {
                state = state.copy(
                    isRequestingApiKey = true,
                )
            }
            ConfigureKeyAction.OnValidate -> handleOnValidate()
        }
    }

    /**
     * This function validates the API key.
     * */
    private fun handleOnValidate() {
        try {
            viewModelScope.launch {
                state = state.copy(
                    isRequestingApiKey = false,
                    isGeminiBeingValidated = true,
                    isPlacesBeingValidated = true,
                )
                val result = getSomePlaceRecommendationsUseCase.invoke(
                    quantity     = 1,
                    customApiKey = state.apiKey,
                )
                state = state.copy(
                    isGeminiBeingValidated = false,
                    isPlacesBeingValidated = false,
                )
                when (result) {
                    is Result.Success -> {
                        val isGeminiValid = result.data.any {
                            it.name.isNotEmpty() &&
                            it.description.isNotEmpty() &&
                            CoordinatesUtils.isValidCoordinates(it.location)
                        }
                        val isPlacesValid = result.data.any {
                            it.imageUrl.isNotEmpty() &&
                            it.imageUrl != DEFAULT_IMAGE_ON_NOT_IMAGE_PROVIDER
                        }
                        state = state.copy(
                            isGeminiBeingValidated = false,
                            isPlacesBeingValidated = false,
                            isGeminiValid          = isGeminiValid,
                            isPlacesValid          = isPlacesValid,
                            enableSave             = isGeminiValid && isPlacesValid,
                        )
                    }
                    is Result.Error -> {
                        state = state.copy(
                            isGeminiBeingValidated = false,
                            isPlacesBeingValidated = false,
                            isGeminiValid          = false,
                            isPlacesValid          = false,
                            enableSave             = false,
                        )
                    }
                }
            }
        } catch (e: Exception) { Unit }
    }
}