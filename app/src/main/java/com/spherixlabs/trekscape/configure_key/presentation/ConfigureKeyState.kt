package com.spherixlabs.trekscape.configure_key.presentation

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR

/**
 * Describe the state [ConfigureKeyState] of the configure apikey screen.
 * @param apiKey [String] The user's apikey.
 * @param enableSave [Boolean] It is activated when the apikey can be saved.
 *
 * */
data class ConfigureKeyState(
    val apiKey     : String = EMPTY_STR,
    val enableSave : Boolean = false
)