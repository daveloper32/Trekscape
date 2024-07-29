package com.spherixlabs.trekscape.place.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData
import com.spherixlabs.trekscape.core.domain.utils.results.DataError
import com.spherixlabs.trekscape.core.domain.utils.results.Result
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.coordinates.CoordinatesUtils
import com.spherixlabs.trekscape.place.data.db.dao.PlaceDao
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity
import com.spherixlabs.trekscape.place.data.db.utils.toPlaceData
import com.spherixlabs.trekscape.place.data.db.utils.toPlaceEntity
import com.spherixlabs.trekscape.place.domain.model.PlaceData
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
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
        amount : Int
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

    override fun getAndSearchPaginated(
        searchQuery       : String,
        showOnlyFavorites : Boolean,
        currentLocation   : CoordinatesData?,
    ): Flow<PagingData<PlaceData>> {
        return try {
            Pager(
                PagingConfig(
                    pageSize         = 10,
                    prefetchDistance = 20,
                )
            ) {
                dao.getAndSearchAllPaged(
                    query             = searchQuery,
                    showOnlyFavorites = showOnlyFavorites,
                )
            }.flow
                .map { value: PagingData<PlaceEntity> ->
                    value.map { placeEntity: PlaceEntity ->
                        placeEntity.toPlaceData().apply {
                            missingMeters = if (currentLocation == null) EMPTY_STR
                                            else CoordinatesUtils.formatDistance(
                                                CoordinatesUtils.calculateDistance(
                                                        lat1 = currentLocation.latitude,
                                                        lon1 = currentLocation.longitude,
                                                        lat2 = location.latitude,
                                                        lon2 = location.longitude,
                                                    )
                                                )
                        }
                    }
                }
        } catch (e: Exception) {
            emptyFlow<PagingData<PlaceData>>()
        }
    }

    override suspend fun updateIsFavoriteById(
        id         : String,
        isFavorite : Boolean
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.updateIsFavorite(
                    id         = id,
                    isFavorite = isFavorite,
                )
                Result.Success(
                    true
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }

    override suspend fun deleteById(
        id : String
    ): Result<Boolean, DataError.DB> {
        return try {
            withContext(Dispatchers.IO) {
                dao.delete(
                    id = id,
                )
                Result.Success(
                    true
                )
            }
        } catch (e: Exception) {
            Result.Error(DataError.DB.UNKNOWN)
        }
    }
}