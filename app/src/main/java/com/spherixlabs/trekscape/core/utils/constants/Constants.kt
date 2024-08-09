package com.spherixlabs.trekscape.core.utils.constants

/**
 * Common constants [Constants] used in the project.
 * */
object Constants {
    /**Attempts available to the user */
    val ATTEMPTS_AVAILABLE = 5
    /** Empty [String] string value */
    val EMPTY_STR = ""
    /** Default [Long] invalid value */
    val LONG_INVALID = -1L
    /** Default [Int] invalid value */
    val INT_INVALID = -1
    /** Default [Int] zero value */
    val INT_ZERO = 0
    /** Default [Int] amount value */
    val DEFAULT_AMOUNT = 100
    /** The name of the Gemini model to use */
    val GEMINI_MODEL_NAME = "gemini-1.5-flash"
    /** A default image url to use when no image is available */
    val DEFAULT_IMAGE_ON_NOT_IMAGE_PROVIDER = "https://upload.wikimedia.org/wikipedia/commons/e/eb/Machu_Picchu%2C_Peru.jpg"
    /** Json Keys for the Place Recommendation response */
    object JsonPlaceRecommendation {
        val NAME = "name"
        val DESCRIPTION = "description"
        val COORDINATES = "coordinates"
        val LATITUDE = "latitude"
        val LONGITUDE = "longitude"
    }
    /** Json Keys for the Place Activity Recommendation response */
    object JsonPlaceActivityRecommendation {
        val NAME = "name"
        val DESCRIPTION = "description"
    }
}