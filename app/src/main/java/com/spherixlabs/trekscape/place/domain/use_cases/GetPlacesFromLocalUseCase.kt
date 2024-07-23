package com.spherixlabs.trekscape.place.domain.use_cases

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_AMOUNT
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import javax.inject.Inject

/**
 * [GetPlacesFromLocalUseCase] represents all the process for getting places from local storage.
 * */
class GetPlacesFromLocalUseCase @Inject constructor(
    private val repository : PlaceRepository,
) {
    /**
     * This function gets a list of places from local storage.
     *
     * @param amount [Int] The amount of places to get.
     * @return [Result]<[List]<[PlaceData]>, [DataError.DB]>
     * */
    suspend operator fun invoke(
        amount : Int = DEFAULT_AMOUNT,
    ): Result<List<PlaceData>, DataError.DB> {
        return repository.get(
            amount = amount
        )
    }
}