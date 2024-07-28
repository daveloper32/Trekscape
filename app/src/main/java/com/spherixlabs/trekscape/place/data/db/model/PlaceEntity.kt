package com.spherixlabs.trekscape.place.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spherixlabs.trekscape.core.data.db.model.CoordinatesDataEntity
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.uuid.UUIDGeneratorUtils

/**
 * [PlaceEntity] is the data class that represents a place entity in the database.
 *
 * @property id [String] The unique identifier of the place.
 * @property name [String] The name of the place.
 * @property description [String] The description of the place.
 * @property imageUrl [String] The URL of the image associated with the place.
 * @property coordinates [CoordinatesDataEntity] The coordinates of the place.
 * @property isFavorite [Boolean] Whether the place is a favorite or not.
 * */
@Entity
data class PlaceEntity(
    @PrimaryKey()
    val id          : String = UUIDGeneratorUtils.generateUUIDWithUTCTimestampAndRandomString(),
    val name        : String = EMPTY_STR,
    val description : String = EMPTY_STR,
    val imageUrl    : String = EMPTY_STR,
    val coordinates : CoordinatesDataEntity = CoordinatesDataEntity(latitude = 0.0, longitude = 0.0),
    val isFavorite  : Boolean = false,
)