package com.spherixlabs.trekscape.core.data.settings

import android.content.Context
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.constants.Constants.LONG_INVALID
import com.spherixlabs.trekscape.core.utils.preferences.putBooleanValue
import com.spherixlabs.trekscape.core.utils.preferences.putIntValue
import com.spherixlabs.trekscape.core.utils.preferences.putLongValue
import com.spherixlabs.trekscape.core.utils.preferences.putStringSetValue
import com.spherixlabs.trekscape.core.utils.preferences.putStringValue
import com.spherixlabs.trekscape.home.domain.enums.LocationPreference
import javax.inject.Inject

/**
 * [UserSettingsImpl] is an implementation of [UserStorage] for handle the settings data on
 * the app.
 * */
class UserSettingsImpl @Inject constructor(
    context : Context
): UserStorage {

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0)

    private val preferencesEditor = sharedPreferences.edit()

    override var name: String
        get() = sharedPreferences.getString(Keys.NAME.key, EMPTY_STR) ?: EMPTY_STR
        set(value) = preferencesEditor.putStringValue(Keys.NAME.key, value)
    override var attempts: Int
        get() =  sharedPreferences.getInt(Keys.ATTEMPTS.key, 0)
        set(value) = preferencesEditor.putIntValue(Keys.ATTEMPTS.key, value)
    override var lastAttempt: Long
        get() = sharedPreferences.getLong(Keys.LAST_ATTEMPT.key, LONG_INVALID)
        set(value) = preferencesEditor.putLongValue(Keys.LAST_ATTEMPT.key, value)

    override var preferences: Set<String>
        get() = sharedPreferences.getStringSet(Keys.PREFERENCES.key, emptySet()) ?: emptySet()
        set(value) = preferencesEditor.putStringSetValue(Keys.PREFERENCES.key, value)

    override var isLocationPreferencesSetAsDonTAskAgain: Boolean
        get() = sharedPreferences.getBoolean(Keys.DON_T_ASK_AGAIN_LOCATION_PREFERENCES.key, false)
        set(value) = preferencesEditor.putBooleanValue(Keys.DON_T_ASK_AGAIN_LOCATION_PREFERENCES.key, value)

    override var locationPreference: LocationPreference
        get() = LocationPreference.fromString(sharedPreferences.getString(Keys.LOCATION_PREFERENCE.key, EMPTY_STR))
        set(value) = preferencesEditor.putStringValue(Keys.LOCATION_PREFERENCE.key, value.name)

    override var apiKey: String
        get() = sharedPreferences.getString(Keys.APIKEY.key, EMPTY_STR) ?: EMPTY_STR
        set(value) = preferencesEditor.putStringValue(Keys.APIKEY.key, value)

    override fun clear() {
        name = EMPTY_STR
        preferences = emptySet()
    }

    companion object {
        private const val PREFERENCES_NAME = "user_settings"
        /**
         * [Keys] specify all the keys used in [UserSettingsImpl]
         * */
        enum class Keys {
            NAME,
            ATTEMPTS,
            LAST_ATTEMPT,
            PREFERENCES,
            APIKEY,
            DON_T_ASK_AGAIN_LOCATION_PREFERENCES,
            LOCATION_PREFERENCE;
            val key get() = this.name
        }
    }
}