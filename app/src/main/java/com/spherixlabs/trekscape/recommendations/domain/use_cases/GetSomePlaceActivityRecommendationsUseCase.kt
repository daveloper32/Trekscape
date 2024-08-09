package com.spherixlabs.trekscape.recommendations.domain.use_cases

import com.spherixlabs.trekscape.core.data.network.utils.NetworkProvider
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.domain.utils.results.map
import com.spherixlabs.trekscape.core.utils.os.OsUtils
import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.activity.domain.use_cases.GetActivitiesByPlaceIdFromLocalUseCase
import com.spherixlabs.trekscape.activity.domain.use_cases.SaveActivitiesInLocalUseCase
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceActivityRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceActivityRecommendationsRepository
import com.spherixlabs.trekscape.recommendations.domain.utils.toActivityData
import javax.inject.Inject

/**
 * [GetSomePlaceActivityRecommendationsUseCase] represents all the process for the getting some number of
 * place recommendations.
 * */
class GetSomePlaceActivityRecommendationsUseCase @Inject constructor(
    private val networkProvider                        : NetworkProvider,
    private val resourceProvider                       : ResourceProvider,
    private val userStorage                            : UserStorage,
    private val repository                             : PlaceActivityRecommendationsRepository,
    private val getActivitiesByPlaceIdFromLocalUseCase : GetActivitiesByPlaceIdFromLocalUseCase,
    private val saveActivitiesInLocalUseCase           : SaveActivitiesInLocalUseCase,
) {
    /**
     * This function gets some number of place recommendations based on previous preferences setup.
     *
     * @param customApiKey [String]? The custom API key to use for the recommendations.
     * @return [Result]<[List]<[PlaceActivityRecommendation]>, [DataError.Network]>
     * */
    suspend operator fun invoke(
        place        : PlaceData,
        customApiKey : String? = null,
    ): Result<List<ActivityData>, DataError.Network> {
        if (place.name.isEmpty()) {
            return Result.Error(DataError.Network.NOT_FOUND)
        }
        val localActivities: List<ActivityData> = getActivitiesFormLocal(place)
        if (localActivities.isNotEmpty()) {
            return Result.Success(localActivities)
        }
        if (!networkProvider.isConnected()) {
            return Result.Error(DataError.Network.NOT_INTERNET)
        }
        val result = repository.getSomeRecommendations(
            placeName = place.name,
            customApiKey       = if (customApiKey != null) {
                customApiKey
            } else {
                if (userStorage.apiKey.isNotEmpty()) {
                    userStorage.apiKey
                } else {
                    null
                }
            },
            ownPreferences     = userStorage.preferences.toList(),
            languageCode = OsUtils.getDeviceLanguage(),
        )
        var placeActivityRecommendations: List<ActivityData> = listOf()
        if (result is Result.Success) {
            placeActivityRecommendations = result.data.map {
                it.toActivityData(
                    placeId = place.id,
                )
            }
            saveRecommendations(placeActivityRecommendations)
        }
        return result.map { placeActivityRecommendations }
    }

    /**
     * This function gets the activities from the local storage.
     *
     * @param place [PlaceData] The place to get the activities from.
     * @return [List]<[ActivityData]> The list of activities.
     * */
    private suspend fun getActivitiesFormLocal(
        place : PlaceData
    ): List<ActivityData> {
        return try {
            val result = getActivitiesByPlaceIdFromLocalUseCase.invoke(
                id = place.id,
            )
            when (result) {
                is Result.Success -> result.data
                else              -> emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * This function saves the recommendations to the local storage.
     *
     * @param data [List]<[ActivityData]> The list of recommendations to save.
     * */
    private suspend fun saveRecommendations(
        data: List<ActivityData>
    ) {
        try {
            saveActivitiesInLocalUseCase.invoke(
                activities = data,
            )
        } catch (e: Exception) { Unit }
    }
}