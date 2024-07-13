package com.spherixlabs.trekscape.historical.domain.model


/**
 * Data class representing user history.
 *
 * @property name [String] The name of the place.
 * @property urlImage [String]  The url image of the place.
 * @property description [String]  The description of the place.
 * @property missingMeters [String]  The missing meters from the user to the place.
 */
data class HistoricalModel(
    val name          : String,
    val urlImage      : String,
    val description   : String,
    val missingMeters : String,
)