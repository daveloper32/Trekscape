package com.spherixlabs.trekscape.place.domain.use_cases

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import javax.inject.Inject

/**
 * [SetPlaceAsFavoriteUseCase] represents all the process for setting a place as favorite or not.
 * */
class SetPlaceAsFavoriteUseCase @Inject constructor(
    private val repository : PlaceRepository
) {
    /**
     * This function updates the [PlaceData.isFavorite] value of a [PlaceData] based on its [PlaceData.id].
     *
     * @param id [String] the [PlaceData.id].
     * @param isFavorite [Boolean] the new value of [PlaceData.isFavorite].
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend operator fun invoke(
        id         : String,
        isFavorite : Boolean,
    ): Result<Boolean, DataError.DB> {
        return repository.updateIsFavoriteById(
            id         = id,
            isFavorite = isFavorite,
        )
    }
}