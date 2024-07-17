package com.spherixlabs.trekscape.home.domain.enums

/**
 * [LocationPreference] is an enum that represents the possible location preferences.
 * */
enum class LocationPreference {
    ALL_WORLD,
    SAME_CONTINENT,
    SAME_COUNTRY,
    SAME_CITY;
    companion object {
        /**
         * This function converts a string to a [LocationPreference] enum value.
         *
         * @param preference [String] the string to convert.
         * @return [LocationPreference] the converted enum value.
         * */
        fun fromString(
            preference: String?
        ): LocationPreference =
            LocationPreference.entries.find { it.name ==  preference } ?: ALL_WORLD
    }
}