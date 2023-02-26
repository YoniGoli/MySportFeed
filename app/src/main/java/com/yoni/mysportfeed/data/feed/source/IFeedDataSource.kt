package com.yoni.mysportfeed.data.feed.source

import com.yoni.mysportfeed.data.feed.model.FeedItemDto

internal interface IFeedDataSource {
    suspend fun fetch(): List<FeedItemDto>
}