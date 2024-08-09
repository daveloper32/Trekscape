package com.spherixlabs.trekscape.activity.domain.use_cases

import com.spherixlabs.trekscape.activity.domain.model.ActivityData
import com.spherixlabs.trekscape.activity.domain.repository.ActivityRepository
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import javax.inject.Inject

/**
 * [GetActivitiesByPlaceIdFromLocalUseCase] represents all the process for getting activities
 * related to a place from local storage.
 * */
class GetActivitiesByPlaceIdFromLocalUseCase @Inject constructor(
    private val repository : ActivityRepository,
) {
    /**
     * This function gets a list of activities from local storage.
     *
     * @param id [String] the [PlaceData.id].
     * @return [Result]<[List]<[ActivityData]>, [DataError.DB]>
     * */
    suspend operator fun invoke(
        id : String,
    ): Result<List<ActivityData>, DataError.DB> {
        return repository.getByPlaceId(
            placeId = id,
        )
    }
}