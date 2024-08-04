package com.spherixlabs.trekscape.core.domain.storage

import com.spherixlabs.trekscape.home.domain.enums.LocationPreference

/**
 * [UserStorage] Interface for storing user data.
 * */
interface UserStorage {
    /**
     * Get/Set user's name
     * */
    var name : String
    /**
     * Get/Set attempts counter
     * */
    var attempts : Int

    /**
     * Get/Set The date of the last attemptsAvailable
     * */
    var lastAttempt: Long
    /**
     * Get/Set user's preferences
     * */
    var preferences : Set<String>
    /**
     * Get/Set user's location preferences Don't ask again feature
     * */
    var isLocationPreferencesSetAsDonTAskAgain : Boolean
    /**
     * Get/Set user's apikey
     * */
    var apiKey : String
    /**
     * Get/Set user's location preference
     * */
    var locationPreference : LocationPreference
    /**
     * Clear all user data
     * */
    fun clear()
}