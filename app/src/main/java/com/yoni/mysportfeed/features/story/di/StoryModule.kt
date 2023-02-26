package com.yoni.mysportfeed.features.story.di

import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.story.data.DefaultStoryRepository
import com.yoni.mysportfeed.features.story.data.IStoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object StoryModule {

    @Provides
    fun provideStoryRepository(
        feedRepository: IFeedRepository
    ): IStoryRepository {
        return DefaultStoryRepository(
            feedRepository
        )
    }
}