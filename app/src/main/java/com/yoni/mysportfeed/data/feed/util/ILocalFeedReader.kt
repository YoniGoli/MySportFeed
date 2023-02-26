package com.yoni.mysportfeed.data.feed.util

import com.yoni.mysportfeed.data.feed.model.ServerResponseDto

interface ILocalFeedReader {

    suspend fun read(): ServerResponseDto?

}