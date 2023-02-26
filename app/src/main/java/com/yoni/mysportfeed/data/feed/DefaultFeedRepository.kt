package com.yoni.mysportfeed.data.feed

import com.yoni.mysportfeed.data.database.AppDatabase
import com.yoni.mysportfeed.data.database.entity.EnrichedMatch
import com.yoni.mysportfeed.data.database.entity.StoryPageEntity
import com.yoni.mysportfeed.data.database.entity.StoryWithPages
import com.yoni.mysportfeed.data.feed.model.FeedItemDto
import com.yoni.mysportfeed.data.feed.source.IFeedDataSource
import com.yoni.mysportfeed.data.feed.util.extension.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

internal class DefaultFeedRepository(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val localSource: IFeedDataSource,
    private val database: AppDatabase
): IFeedRepository {

    private val mutex = Mutex()
    private var isReady = false

    override suspend fun refreshFeedItems() {
        withContext(coroutineDispatcher) {
            if(database.matchesDao().totalMatches() < 1) {
                val result = localSource.fetch()
                updateEntities(result)
            }

            isReady = true
        }
    }

    override fun getAllMatches(): Flow<List<EnrichedMatch>> = database.matchesDao().getAll()

    override suspend fun getStoryById(storyId: String): StoryWithPages? = mutex.withLock {
        withContext(coroutineDispatcher){
            database.storyDao().getStoryById(storyId)
        }
    }

    override suspend fun getMatchById(matchId: String): EnrichedMatch? = mutex.withLock {
        withContext(coroutineDispatcher){
            database.matchesDao().getMatch(matchId)
        }
    }

    override fun isReady() = isReady

    private fun updateEntities(feedItems: List<FeedItemDto>) {
        feedItems.forEach { feedItem ->
            database.teamDao().insertAll(feedItem.teams.home.toEntity())
            database.teamDao().insertAll(feedItem.teams.away.toEntity())
            database.leagueDao().insertAll(feedItem.league.toEntity())
            database.venueDao().insertAll(feedItem.fixture.venue.toEntity())

            database.matchesDao().insertAll(feedItem.toEntity(
                homeTeamId = feedItem.teams.home.id,
                awayTeamId = feedItem.teams.away.id,
                leagueId = feedItem.league.id,
                venueId = feedItem.fixture.venue.id
            ))

            feedItem.game?.story?.also { story ->
                database.storyDao().insertStory(story.toEntity(feedItem.id))

                story.pages.forEach { storyPage ->
                    database.storyDao().insertPage(storyPage.toEntity(story.id))
                }
            }
        }
    }

}