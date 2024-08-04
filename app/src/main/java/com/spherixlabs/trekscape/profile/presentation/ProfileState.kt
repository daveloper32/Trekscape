package com.spherixlabs.trekscape.profile.presentation

import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.welcome.domain.model.PreferenceModel

/**
 * Describe the state [ProfileState] of the profile screen.
 *
 * @param userName [String] this is the username.
 * @param apiKey [String] this is the user's apikey.
 * @param natureAdventurePreferences [List]<[String]> The list of nature adventure preferences.
 * @param cultureHistoryPreferences [List]<[String]> The list of culture history preferences.
 * @param relaxationPreferences [List]<[String]> The list of relaxation preferences.
 * @param locationPreference [LocationPreference] The location preference.
 * */
data class ProfileState(
    val userName                   : String  = EMPTY_STR,
    val apiKey                     : String  = EMPTY_STR,
    val natureAdventurePreferences : List<PreferenceModel> = emptyList(),
    val cultureHistoryPreferences  : List<PreferenceModel> = emptyList(),
    val relaxationPreferences      : List<PreferenceModel> = emptyList(),
    val locationPreference         : LocationPreference = LocationPreference.ALL_WORLD,
)