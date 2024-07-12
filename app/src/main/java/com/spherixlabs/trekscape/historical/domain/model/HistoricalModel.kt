package com.spherixlabs.trekscape.historical.domain.model


/**
 * Data class representing user history.
 *
 * @property name [String] The name of the place.
 * @property urlImage [String]  The url image of the place.
 */
data class HistoricalModel(
    val name     : String,
    val urlImage : String,
)