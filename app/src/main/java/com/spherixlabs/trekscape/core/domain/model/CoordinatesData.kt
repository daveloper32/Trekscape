package com.spherixlabs.trekscape.core.domain.model

/**
 * [CoordinatesData] is a data class that represents the coordinates of a location.
 *
 * @property latitude [Double] The latitude of the location.
 * @property longitude [Double] The longitude of the location.
 * */
data class CoordinatesData(
    val latitude  : Double,
    val longitude : Double,
)