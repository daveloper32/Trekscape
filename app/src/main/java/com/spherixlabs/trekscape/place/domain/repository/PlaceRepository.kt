package com.spherixlabs.trekscape.place.domain.repository

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_AMOUNT
import com.spherixlabs.trekscape.place.domain.model.PlaceData

interface PlaceRepository {
    /**
     * This function saves a list of places from the local storage.
     *
     * @param places [List]<[PlaceData]> The list of places to be saved.
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend fun add(
        places : List<PlaceData>,
    ): Result<Boolean, DataError.DB>
    /**
     * This function gets a list of places from the local storage.
     *
     * @param amount [Int] The amount of places to be retrieved.
     * @return [Result]<[List]<[PlaceData]>, [DataError.DB]>
     * */
    suspend fun get(
        amount : Int = DEFAULT_AMOUNT,
    ): Result<List<PlaceData>, DataError.DB>
}