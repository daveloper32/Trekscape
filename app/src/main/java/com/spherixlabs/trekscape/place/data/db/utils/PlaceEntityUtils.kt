package com.spherixlabs.trekscape.place.data.db.utils

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
)

/**
 * Convert [PlaceData] to [PlaceEntity]
 * */
fun PlaceData.toPlaceEntity() = PlaceEntity(
    name        = this.name,
    description = this.description,
    imageUrl    = this.imageUrl,
)