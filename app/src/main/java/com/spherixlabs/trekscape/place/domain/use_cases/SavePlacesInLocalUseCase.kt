package com.spherixlabs.trekscape.place.domain.use_cases

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import javax.inject.Inject

/**
 * [SavePlacesInLocalUseCase] represents all the process for saving places in local storage.
 * */
class SavePlacesInLocalUseCase @Inject constructor(
    private val repository : PlaceRepository,
) {
    /**
     * This function saves a list of places in local storage.
     *
     * @param places [List]<[PlaceData]> The list of places to save.
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend operator fun invoke(
        places : List<PlaceData>
    ): Result<Boolean, DataError.DB> {
        return repository.add(places)
    }
}