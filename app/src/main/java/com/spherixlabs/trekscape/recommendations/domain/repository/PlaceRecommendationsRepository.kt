package com.spherixlabs.trekscape.recommendations.domain.repository

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.os.OsUtils
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation

interface PlaceRecommendationsRepository {
    /**
     * This function retrieves a list of place recommendations based on the specified parameters.
     *
     * @param quantity [Int] The number of recommendations to retrieve.
     * @param ownPreferences [List]<[String]> The list of preferences that the user has set.
     * @param locationPreference [LocationPreference] The location preference for the recommendations.
     * @param currentLocation [CoordinatesData]? The current location of the user. It will only be
     * used if if the location preference is not set to [LocationPreference.ALL_WORLD].
     * @param languageCode [String] The language code for the recommendations.
     * @param placesToSkip [List]<[String]> The list of places to skip.
     * @return [Result]<[List]<[PlaceRecommendation]>, [DataError.Network]>
     * */
    suspend fun getSomeRecommendations(
        quantity           : Int = 5,
        ownPreferences     : List<String> = emptyList(),
        locationPreference : LocationPreference = LocationPreference.ALL_WORLD,
        currentLocation    : CoordinatesData? = null,
        languageCode       : String = OsUtils.getDeviceLanguage(),
        placesToSkip       : List<String> = emptyList(),
    ): Result<List<PlaceRecommendation>, DataError.Network>
}