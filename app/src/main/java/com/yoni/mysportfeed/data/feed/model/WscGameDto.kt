package com.yoni.mysportfeed.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WscGameDto(
    @Json(name = "primeStory") val story: WscGamePrimeStoryDto?
)

@JsonClass(generateAdapter = true)
data class WscGamePrimeStoryDto(
    @Json(name = "storyId") val id: String,
    val title: String,
    val publishDate: String,
    @Json(name = "storyThumbnail") val thumbnail: String,
    @Json(name = "storyThumbnailSquare") val thumbnailSquare: String,
    @Json(name = "pages") val pages: List<WscGameStoryPageDto>,
)


@JsonClass(generateAdapter = true)
data class WscGameStoryPageDto(
    @Json(name = "paggeId") val id: String,
    val duration: Int,
    val videoUrl: String,
    val title: String?,
    val awayScore: Int?,
    val homeScore: Int?,
    val eventType: String?,
    val actionType: String?,
    val gameClock: String?,
    val rating: Int,
    val period: String?,

    )

