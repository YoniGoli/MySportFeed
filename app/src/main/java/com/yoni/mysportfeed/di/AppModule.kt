package com.yoni.mysportfeed.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yoni.mysportfeed.data.feed.DefaultFeedRepository
import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.data.feed.source.FeedLocalDataSource
import com.yoni.mysportfeed.data.feed.source.IFeedDataSource
import com.yoni.mysportfeed.data.feed.util.ILocalFeedReader
import com.yoni.mysportfeed.data.feed.util.DefaultLocalFeedReader
import com.yoni.mysportfeed.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalFeedDataSource

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mySportFeed.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideMoshi(): Moshi{
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideFeedLocalReader(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): ILocalFeedReader = DefaultLocalFeedReader(context.resources, moshi)

    @LocalFeedDataSource
    @Provides
    fun provideFeedLocalSource(
        localFeedReader: ILocalFeedReader
    ): IFeedDataSource = FeedLocalDataSource(localFeedReader)

    @Singleton
    @Provides
    fun provideFeedRepository(
        coroutineDispatcher: CoroutineDispatcher,
        @LocalFeedDataSource localSource: IFeedDataSource,
        database: AppDatabase
    ): IFeedRepository {
        return DefaultFeedRepository(
            coroutineDispatcher = coroutineDispatcher,
            localSource = localSource,
            database = database
        )
    }

}