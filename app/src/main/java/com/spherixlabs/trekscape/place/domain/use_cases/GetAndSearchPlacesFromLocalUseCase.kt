package com.spherixlabs.trekscape.place.domain.use_cases

import androidx.paging.PagingData
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * [GetAndSearchPlacesFromLocalUseCase] represents all the process for getting and searching places
 * paginated in real time from local storage.
 * */
class GetAndSearchPlacesFromLocalUseCase @Inject constructor(
    private val repository : PlaceRepository,
) {
    /**
     * This function gets a list of places from the local storage using pagination.
     *
     * @param query [String] some query to filter and search results
     * @param showOnlyFavorites [Boolean] a flag to show only favorites places
     * @return [Flow]<[PagingData]<[PlaceData]>>
     * */
    operator fun invoke(
        query             : String = EMPTY_STR,
        showOnlyFavorites : Boolean = false,
        coordinatesData   : CoordinatesData? = null
    ): Flow<PagingData<PlaceData>> {
        return repository.getAndSearchPaginated(
            coordinatesData   = coordinatesData,
            searchQuery       = query,
            showOnlyFavorites = showOnlyFavorites,
        )
    }
}