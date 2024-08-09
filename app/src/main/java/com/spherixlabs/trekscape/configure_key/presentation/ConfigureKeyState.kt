package com.spherixlabs.trekscape.configure_key.presentation

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * Describe the state [ConfigureKeyState] of the configure apikey screen.
 * @param isRequestingApiKey [Boolean] It is activated when the apikey is being requested.
 * @param apiKey [String] The user's apikey.
 * @param enableValidate [Boolean] It is activated when the apikey is valid.
 * @param isGeminiBeingValidated [Boolean] It is activated when the gemini apikey is being validated.
 * @param isPlacesBeingValidated [Boolean] It is activated when the places apikey is being validated.
 * @param isGeminiValid [Boolean] It is activated when the gemini apikey is valid.
 * @param isPlacesValid [Boolean] It is activated when the places apikey is valid.
 * @param enableSave [Boolean] It is activated when the apikey can be saved.
 *
 * */
data class ConfigureKeyState(
    val isRequestingApiKey     : Boolean = true,
    val apiKey                 : String = EMPTY_STR,
    val enableValidate         : Boolean = false,
    val isGeminiBeingValidated : Boolean = false,
    val isPlacesBeingValidated : Boolean = false,
    val isGeminiValid          : Boolean = false,
    val isPlacesValid          : Boolean = false,
    val enableSave             : Boolean = false,
)