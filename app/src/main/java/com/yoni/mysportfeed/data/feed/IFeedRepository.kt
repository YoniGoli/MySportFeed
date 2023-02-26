package com.yoni.mysportfeed.data.feed

import com.yoni.mysportfeed.data.database.entity.EnrichedMatch
import com.yoni.mysportfeed.data.database.entity.StoryWithPages
import kotlinx.coroutines.flow.Flow

internal interface IFeedRepository {
    suspend fun refreshFeedItems()
    fun getAllMatches(): Flow<List<EnrichedMatch>>
    suspend fun getStoryById(storyId: String): StoryWithPages?
    suspend fun getMatchById(matchId: String): EnrichedMatch?

    fun isReady(): Boolean
}