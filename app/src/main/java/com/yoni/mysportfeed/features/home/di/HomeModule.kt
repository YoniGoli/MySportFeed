package com.yoni.mysportfeed.features.home.di

import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.home.data.DefaultHomeRepository
import com.yoni.mysportfeed.features.home.data.IHomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object HomeModule {

    @Provides
    fun provideHomeRepository(
        feedRepository: IFeedRepository
    ): IHomeRepository {
        return DefaultHomeRepository(
            feedRepository = feedRepository
        )
    }

}