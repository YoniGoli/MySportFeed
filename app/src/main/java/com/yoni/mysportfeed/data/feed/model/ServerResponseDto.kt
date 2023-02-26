package com.yoni.mysportfeed.data.feed.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerResponseDto(
    val response: List<FeedItemDto>
)