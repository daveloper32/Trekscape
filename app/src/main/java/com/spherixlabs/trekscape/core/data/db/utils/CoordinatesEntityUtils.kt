package com.spherixlabs.trekscape.core.data.db.utils

import com.spherixlabs.trekscape.core.data.db.model.CoordinatesDataEntity
import com.spherixlabs.trekscape.core.domain.model.CoordinatesData

/**
 * Convert [CoordinatesDataEntity] to [CoordinatesData]
 * */
fun CoordinatesDataEntity.toCoordinatesData() = CoordinatesData(
    latitude  = this.latitude,
    longitude = this.longitude,
)

/**
 * Convert [CoordinatesData] to [CoordinatesDataEntity]
 * */
fun CoordinatesData.toCoordinatesDataEntity() = CoordinatesDataEntity(
    latitude  = this.latitude,
    longitude = this.longitude,
)