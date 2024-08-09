package com.spherixlabs.trekscape.di

import com.spherixlabs.trekscape.about.data.github_profile.GithubProfileRepositoryImpl
import com.spherixlabs.trekscape.about.domain.repository.GithubProfileRepository
import com.spherixlabs.trekscape.activity.data.ActivityRepositoryImpl
import com.spherixlabs.trekscape.activity.domain.repository.ActivityRepository
import com.spherixlabs.trekscape.place.data.PlaceRepositoryImpl
import com.spherixlabs.trekscape.place.domain.repository.PlaceRepository
import com.spherixlabs.trekscape.recommendations.data.PlaceActivityRecommendationsRepositoryImpl
import com.spherixlabs.trekscape.recommendations.data.PlaceRecommendationsRepositoryImpl
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceActivityRecommendationsRepository
import com.spherixlabs.trekscape.recommendations.domain.repository.PlaceRecommendationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**DI function that provides a singleton of a [PlaceRecommendationsRepository]*/
    @Binds
    @Singleton
    abstract fun providePlaceRecommendationsRepository(
        repoImpl : PlaceRecommendationsRepositoryImpl
    ): PlaceRecommendationsRepository

    /**DI function that provides a singleton of a [PlaceActivityRecommendationsRepository]*/
    @Binds
    @Singleton
    abstract fun providePlaceActivityRecommendationsRepository(
        repoImpl : PlaceActivityRecommendationsRepositoryImpl
    ): PlaceActivityRecommendationsRepository

    /**DI function that provides a singleton of a [PlaceRepository]*/
    @Binds
    @Singleton
    abstract fun providePlaceRepository(
        repoImpl : PlaceRepositoryImpl,
    ): PlaceRepository

    /**DI function that provides a singleton of a [ActivityRepository]*/
    @Binds
    @Singleton
    abstract fun provideActivityRepository(
        repoImpl : ActivityRepositoryImpl,
    ): ActivityRepository

    /**DI function that provides a singleton of a [GithubProfileRepository]*/
    @Binds
    @Singleton
    abstract fun provideGithubProfileRepository(
        repoImpl : GithubProfileRepositoryImpl,
    ): GithubProfileRepository
}