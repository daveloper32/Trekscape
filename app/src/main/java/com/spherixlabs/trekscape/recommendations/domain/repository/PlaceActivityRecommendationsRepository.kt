package com.spherixlabs.trekscape.recommendations.domain.repository

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.os.OsUtils
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceActivityRecommendation
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation

interface PlaceActivityRecommendationsRepository {
    /**
     * This function retrieves a list of place activity recommendations based on the specified parameters.
     *
     * @param placeName [String] The name of the place for which to retrieve recommendations.
     * @param customApiKey [String]? The custom API key to use for the recommendations.
     * @param ownPreferences [List]<[String]> The list of preferences that the user has set.
     * @param languageCode [String] The language code for the recommendations.
     * @return [Result]<[List]<[PlaceRecommendation]>, [DataError.Network]>
     * */
    suspend fun getSomeRecommendations(
        placeName          : String,
        customApiKey       : String? = null,
        ownPreferences     : List<String> = emptyList(),
        languageCode       : String = OsUtils.getDeviceLanguage(),
    ): Result<List<PlaceActivityRecommendation>, DataError.Network>
}