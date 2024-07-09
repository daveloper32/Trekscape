package com.spherixlabs.trekscape.core.data.settings

import android.content.Context
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.utils.constants.Constants.EMPTY_STR
import com.spherixlabs.trekscape.core.utils.preferences.putStringSetValue
import com.spherixlabs.trekscape.core.utils.preferences.putStringValue
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

    override var preferences: Set<String>
        get() = sharedPreferences.getStringSet(Keys.PREFERENCES.key, emptySet()) ?: emptySet()
        set(value) = preferencesEditor.putStringSetValue(Keys.PREFERENCES.key, value)


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
            NAME, PREFERENCES;
            val key get() = this.name
        }
    }
}