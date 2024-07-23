package com.spherixlabs.trekscape.place.data

import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.place.data.db.dao.PlaceDao
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity
import com.spherixlabs.trekscape.place.data.db.utils.toPlaceData
import com.spherixlabs.trekscape.place.data.db.utils.toPlaceEntity
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * [PlaceRepositoryImpl] is a implementation of [PlaceRepository].
 * */
class PlaceRepositoryImpl @Inject constructor(
    private val dao : PlaceDao,
): PlaceRepository {

    override suspend fun add(
        places : List<PlaceData>,
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.insertAll(
                    places.map {
                        it.toPlaceEntity()
                    }
                )
                Result.Success(true)
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }

    override suspend fun get(
        amount: Int
    ): Result<List<PlaceData>, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                val data: List<PlaceEntity> = dao
                    .getPlaces(limit = amount)
                Result.Success(
                    data.map {
                        it.toPlaceData()
                    }
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }
}