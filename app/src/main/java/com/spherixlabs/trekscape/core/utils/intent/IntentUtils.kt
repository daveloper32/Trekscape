package com.spherixlabs.trekscape.core.utils.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * [IntentUtils] is a utility class that provides methods for easily creating and launching intents.
 * */
object IntentUtils {
    /**
     * Navigates to the application details settings screen.
     *
     * @param context [Context] The application context.
     * */
    fun goToAppDetailsSettings(
        context : Context
    ) {
        try {
            // Creating the intent object
            val i: Intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            i.data = Uri.parse("package:${context.packageName}")
            // Start the intent
            context.startActivity(i)
        } catch (e: Exception) { Unit }
    }
    /**
     * Navigates to the device's location settings screen.
     *
     * @param context [Context] The application context.
     * */
    fun goToLocationSourceSettings(
        context : Context
    ) {
        try {
            val intent = Intent().apply {
                action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            }
            context.startActivity(intent)
        } catch (e: Exception) { Unit }
    }
    /**
     * Navigates to some URL.
     *
     * @param context [Context] The application context.
     * @param url     [String] The URL to navigate to.
     * */
    fun goToWebPage(
        context : Context,
        url     : String,
    ) {
        try {
            val webpage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            context.startActivity(intent)
        } catch (e: Exception) { Unit }
    }
}