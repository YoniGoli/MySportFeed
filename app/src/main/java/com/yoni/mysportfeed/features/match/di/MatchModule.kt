package com.yoni.mysportfeed.features.match.di

import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.match.data.DefaultMatchRepository
import com.yoni.mysportfeed.features.match.data.IMatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object MatchModule {

    @Provides
    fun provideMatchRepository(
        feedRepository: IFeedRepository
    ): IMatchRepository {
        return DefaultMatchRepository(
            feedRepository = feedRepository
        )
    }

}