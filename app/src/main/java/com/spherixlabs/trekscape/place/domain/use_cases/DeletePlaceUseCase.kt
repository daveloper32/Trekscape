package com.spherixlabs.trekscape.place.domain.use_cases

import com.spherixlabs.trekscape.activity.domain.use_cases.DeleteActivitiesByPlaceIdUseCase
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import javax.inject.Inject

/**
 * [DeletePlaceUseCase] represents all the process for deleting a place.
 * */
class DeletePlaceUseCase @Inject constructor(
    private val repository                       : PlaceRepository,
    private val deleteActivitiesByPlaceIdUseCase : DeleteActivitiesByPlaceIdUseCase,
) {
    /**
     * This function deletes a [PlaceData] based on its [PlaceData.id].
     *
     * @param id [String] the [PlaceData.id].
     * @return [Result]<[Boolean], [DataError.DB]>
     * */
    suspend operator fun invoke(
        id : String,
    ): Result<Boolean, DataError.DB> {
        return repository.deleteById(
            id = id,
        ).also {
            deleteActivitiesByPlaceIdUseCase.invoke(
                id = id
            )
        }
    }
}