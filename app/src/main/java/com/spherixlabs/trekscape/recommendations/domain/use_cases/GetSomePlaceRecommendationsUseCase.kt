package com.spherixlabs.trekscape.recommendations.domain.use_cases

import com.spherixlabs.trekscape.core.data.network.utils.NetworkProvider
import com.spherixlabs.trekscape.core.data.provider.ResourceProvider
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import com.spherixlabs.trekscape.recommendations.domain.model.PlaceRecommendation
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import javax.inject.Inject

/**
 * [GetSomePlaceRecommendationsUseCase] represents all the process for the getting some number of
 * place recommendations.
 * */
class GetSomePlaceRecommendationsUseCase @Inject constructor(
    private val networkProvider  : NetworkProvider,
    private val resourceProvider : ResourceProvider,
    private val userStorage      : UserStorage,
    private val repository       : PlaceRecommendationsRepository,
) {
    /**
     * This function gets some number of place recommendations based on previous preferences setup.
     *
     * @param quantity [Int] The number of recommendations to retrieve.
     * @return [Result]<[List]<[PlaceRecommendation]>, [DataError.Network]>
     * */
    suspend operator fun invoke(
        quantity : Int = DEFAULT_QUANTITY,
    ): Result<List<PlaceRecommendation>, DataError.Network> {
        if (!networkProvider.isConnected()) {
            return Result.Error(DataError.Network.NOT_INTERNET)
        }
        return repository.getSomeRecommendations(
            quantity           = quantity,
            ownPreferences     = userStorage.preferences.toList(),
            locationPreference = userStorage.locationPreference,
            currentLocation    = if (userStorage.locationPreference != LocationPreference.ALL_WORLD) {
                resourceProvider.getCurrentCoordinates()
            } else {
                null
            }
        )
    }
    companion object {
        private const val DEFAULT_QUANTITY = 5
    }
}