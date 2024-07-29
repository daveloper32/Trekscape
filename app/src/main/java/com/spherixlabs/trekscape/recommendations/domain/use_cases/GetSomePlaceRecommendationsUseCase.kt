package com.spherixlabs.trekscape.recommendations.domain.use_cases

import com.spherixlabs.trekscape.core.data.network.utils.NetworkProvider
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.utils.results.map
import com.spherixlabs.trekscape.core.utils.os.OsUtils
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.use_cases.GetPlacesFromLocalUseCase
import com.spherixlabs.trekscape.place.domain.use_cases.SavePlacesInLocalUseCase
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import com.spherixlabs.trekscape.recommendations.domain.utils.toPlaceData
import javax.inject.Inject

/**
 * [GetSomePlaceRecommendationsUseCase] represents all the process for the getting some number of
 * place recommendations.
 * */
class GetSomePlaceRecommendationsUseCase @Inject constructor(
    private val networkProvider           : NetworkProvider,
    private val resourceProvider          : ResourceProvider,
    private val userStorage               : UserStorage,
    private val repository                : PlaceRecommendationsRepository,
    private val savePlacesInLocalUseCase  : SavePlacesInLocalUseCase,
    private val getPlacesFromLocalUseCase : GetPlacesFromLocalUseCase,
) {
    /**
     * This function gets some number of place recommendations based on previous preferences setup.
     *
     * @param quantity [Int] The number of recommendations to retrieve.
     * @return [Result]<[List]<[PlaceData]>, [DataError.Network]>
     * */
    suspend operator fun invoke(
        quantity : Int = DEFAULT_QUANTITY,
    ): Result<List<PlaceData>, DataError.Network> {
        if (!networkProvider.isConnected()) {
            return Result.Error(DataError.Network.NOT_INTERNET)
        }
        val result = repository.getSomeRecommendations(
            quantity           = quantity,
            ownPreferences     = userStorage.preferences.toList(),
            locationPreference = userStorage.locationPreference,
            currentLocation    = if (resourceProvider.isGPSEnabled()) {
                resourceProvider.getCurrentCoordinates()
            } else {
                null
            },
            languageCode = OsUtils.getDeviceLanguage(),
            placesToSkip = getOldPlaceRecommendations(),
        )
        var placeRecommendations: List<PlaceData> = listOf()
        if (result is Result.Success) {
            placeRecommendations = result.data.map { it.toPlaceData() }
            saveRecommendations(placeRecommendations)
        }
        return result.map { placeRecommendations }
    }

    /**
     * This function saves the recommendations to the local storage.
     *
     * @param data [List]<[PlaceData]> The list of recommendations to save.
     * */
    private suspend fun saveRecommendations(
        data: List<PlaceData>
    ) {
        try {
            savePlacesInLocalUseCase.invoke(
                data
            )
        } catch (e: Exception) { Unit }
    }

    /**
     * This function gets the old place recommendations from the local storage.
     * */
    private suspend fun getOldPlaceRecommendations(
    ): List<String> {
        return try {
            val result = getPlacesFromLocalUseCase.invoke()
            if (result is Result.Success) {
                result.data.map { it.name }
            } else {
                emptyList<String>()
            }
        } catch (e: Exception) {
            emptyList<String>()
        }
    }

    companion object {
        private const val DEFAULT_QUANTITY = 5
    }
}