package com.spherixlabs.trekscape.place.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spherixlabs.trekscape.core.utils.constants.Constants.DEFAULT_AMOUNT
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity

/**
 * [PlaceDao] is a DAO interface for the [PlaceEntity] entity.
 * */
@Dao
interface PlaceDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    /**
     * This function inserts a [PlaceEntity] into the database.
     *
     * @param place [PlaceEntity] The [PlaceEntity] to insert into the database.
     * */
    fun insert(
        place: PlaceEntity
    )
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    /**
     * This function inserts a list of [PlaceEntity] into the database.
     *
     * @param places [List]<[PlaceEntity]> The list of [PlaceEntity] to insert into the database.
     * */
    fun insertAll(
        places : List<PlaceEntity>
    )
    @Query("SELECT * FROM PlaceEntity ORDER BY id DESC LIMIT :limit")
    /**
     * This function gets a list of [PlaceEntity] from the database with a limit of [limit].
     *
     * @param limit [Int] The maximum number of [PlaceEntity] to retrieve.
     * @return [List]<[PlaceEntity]> The list of [PlaceEntity] retrieved from the database.
     * */
    fun getPlaces(
        limit : Int = DEFAULT_AMOUNT,
    ) : List<PlaceEntity>
    @Query("SELECT * FROM PlaceEntity WHERE LOWER(name) LIKE LOWER('%' || :query || '%') AND isFavorite = :showOnlyFavorites ORDER BY id DESC")
    /**
     * This function gets the [PlaceEntity] from the database using pagination.
     *
     * @param query [String] a search query to filter by name
     * @param showOnlyFavorites [Boolean] if wants to filter and show only the favorites or all results
     * @return [PagingSource]<[Int], [PlaceEntity]> The [PagingSource] of [PlaceEntity] retrieved from the database.
     * */
    fun getAndSearchAllPaged(
        query             : String = EMPTY_STR,
        showOnlyFavorites : Boolean = false,
    ): PagingSource<Int, PlaceEntity>
}