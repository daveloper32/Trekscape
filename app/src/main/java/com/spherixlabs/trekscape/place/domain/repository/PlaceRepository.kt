package com.spherixlabs.trekscape.place.domain.repository

import androidx.paging.PagingData
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_AMOUNT
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import kotlinx.coroutines.flow.Flow

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
    /**
     * This function gets a list of places from the local storage using pagination.
     *
     * @param searchQuery [String] some query to filter and search results
     * @param showOnlyFavorites [Boolean] a flag to show only favorites results or all results
     * @return [Flow]<[PagingData]<[PlaceData]>>
     * */
    fun getAndSearchPaginated(
        searchQuery       : String = EMPTY_STR,
        showOnlyFavorites : Boolean = false,
    ): Flow<PagingData<PlaceData>>
}