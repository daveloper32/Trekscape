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
    /**
     * This function inserts a [PlaceEntity] into the database.
     *
     * @param place [PlaceEntity] The [PlaceEntity] to insert into the database.
     * */
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insert(
        place: PlaceEntity
    )
    /**
     * This function inserts a list of [PlaceEntity] into the database.
     *
     * @param places [List]<[PlaceEntity]> The list of [PlaceEntity] to insert into the database.
     * */
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAll(
        places : List<PlaceEntity>
    )
    /**
     * This function gets a list of [PlaceEntity] from the database with a limit of [limit].
     *
     * @param limit [Int] The maximum number of [PlaceEntity] to retrieve.
     * @return [List]<[PlaceEntity]> The list of [PlaceEntity] retrieved from the database.
     * */
    @Query("SELECT * FROM PlaceEntity ORDER BY id DESC LIMIT :limit")
    fun getPlaces(
        limit : Int = DEFAULT_AMOUNT,
    ) : List<PlaceEntity>
    /**
     * This function gets the [PlaceEntity] from the database using pagination.
     *
     * @param query [String] a search query to filter by name
     * @param showOnlyFavorites [Boolean] if wants to filter and show only the favorites or all results
     * @return [PagingSource]<[Int], [PlaceEntity]> The [PagingSource] of [PlaceEntity] retrieved from the database.
     * */
    @Query("""
        SELECT * FROM PlaceEntity 
        WHERE LOWER(name) LIKE LOWER('%' || :query || '%') 
        AND (:showOnlyFavorites = 0 OR isFavorite = 1) 
        ORDER BY id DESC
        """
    )
    fun getAndSearchAllPaged(
        query             : String = EMPTY_STR,
        showOnlyFavorites : Boolean = false,
    ): PagingSource<Int, PlaceEntity>
    /**
     * This function updates the [PlaceEntity.isFavorite] value of a [PlaceEntity] based on its
     * [PlaceEntity.id].
     * */
    @Query("UPDATE PlaceEntity SET isFavorite = :isFavorite WHERE id = :id")
    fun updateIsFavorite(
        id         : String,
        isFavorite : Boolean,
    )
}