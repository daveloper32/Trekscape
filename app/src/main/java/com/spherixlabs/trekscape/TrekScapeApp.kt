package com.spherixlabs.trekscape

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * [TrekScapeApp] is the application class for the TrekScape app. Where some DI and Logging
 * configuration is done.
 * */
@HiltAndroidApp
class TrekScapeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initView()
    }

    private fun initView() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}