package com.spherixlabs.trekscape.place.data.db.utils

import com.spherixlabs.trekscape.core.data.db.utils.toCoordinatesData
import com.spherixlabs.trekscape.core.data.db.utils.toCoordinatesDataEntity
import com.spherixlabs.trekscape.place.data.db.model.PlaceEntity
import com.spherixlabs.trekscape.place.domain.model.PlaceData

/**
 * Convert [PlaceEntity] to [PlaceData]
 * */
fun PlaceEntity.toPlaceData() = PlaceData(
    id          = this.id,
    name        = this.name,
    description = this.description,
    imageUrl    = this.imageUrl,
    coordinates = this.coordinates.toCoordinatesData(),
    isFavorite  = this.isFavorite,
)

/**
 * Convert [PlaceData] to [PlaceEntity]
 * */
fun PlaceData.toPlaceEntity() = PlaceEntity(
    id          = this.id,
    name        = this.name,
    description = this.description,
    imageUrl    = this.imageUrl,
    coordinates = this.coordinates.toCoordinatesDataEntity(),
    isFavorite  = this.isFavorite,
)