package com.spherixlabs.trekscape.core.domain.storage

/**
 * [UserStorage] Interface for storing user data.
 * */
interface UserStorage {
    /**
     * Get/Set user's name
     * */
    var name : String
    /**
     * Get/Set user's preferences
     * */
    var preferences : Set<String>
    /**
     * Clear all user data
     * */
    fun clear()
}