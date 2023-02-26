package com.yoni.mysportfeed.data.feed.source

import com.yoni.mysportfeed.data.feed.model.FeedItemDto
import com.yoni.mysportfeed.data.feed.util.ILocalFeedReader

internal class FeedLocalDataSource(
    private val localFeedReader: ILocalFeedReader,
): IFeedDataSource {

    override suspend fun fetch(): List<FeedItemDto> {
        return localFeedReader.read()?.let {
            it.response
        } ?: listOf()
    }


}