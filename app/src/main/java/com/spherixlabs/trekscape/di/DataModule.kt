package com.spherixlabs.trekscape.di

import android.app.Application
import com.spherixlabs.trekscape.activity.data.db.dao.ActivityDao
import com.spherixlabs.trekscape.core.data.db.TrekScapeDatabase
import com.spherixlabs.trekscape.core.data.settings.PermissionsStateSettingsImpl
import com.spherixlabs.trekscape.core.data.settings.UserSettingsImpl
import com.spherixlabs.trekscape.core.domain.storage.PermissionsStateStorage
import com.spherixlabs.trekscape.core.domain.storage.UserStorage
import com.spherixlabs.trekscape.core.utils.room.createARoomDatabase
import com.spherixlabs.trekscape.place.data.db.dao.PlaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Provides a [HttpClient] instance with the necessary configurations.
     *
     * @return [HttpClient]
     * */
    @Provides
    @Singleton
    fun provideHttpClient(
    ): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }

    /**DI function that provides a singleton of a [TrekScapeDatabase]*/
    @Provides
    @Singleton
    fun provideTrekScapeDatabase(
        app: Application
    ): TrekScapeDatabase = createARoomDatabase<TrekScapeDatabase>(
        context = app,
        name    = TrekScapeDatabase.DATABASE_NAME,
    )

    /**DI function that provides a singleton of a [PlaceDao]*/
    @Provides
    @Singleton
    fun providePlaceDao(
        db: TrekScapeDatabase
    ): PlaceDao = db.placeDao

    /**DI function that provides a singleton of a [ActivityDao]*/
    @Provides
    @Singleton
    fun provideActivityDao(
        db: TrekScapeDatabase
    ): ActivityDao = db.activityDao

    /**DI function that provides a singleton of a [UserStorage]*/
    @Provides
    @Singleton
    fun provideUserStorage(
        app: Application
    ): UserStorage = UserSettingsImpl(app)

    /**DI function that provides a singleton of a [PermissionsStateStorage]*/
    @Provides
    @Singleton
    fun providePermissionStateStorage(
        app: Application
    ): PermissionsStateStorage = PermissionsStateSettingsImpl(app)
}