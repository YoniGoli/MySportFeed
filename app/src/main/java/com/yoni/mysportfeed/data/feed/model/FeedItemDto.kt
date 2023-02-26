package com.yoni.mysportfeed.data.feed.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedItemDto(
    @Json(name = "WSCGameId") val id: String,
    val teams: TeamsDto,
    val league: LeagueDto,
    val fixture: FixtureDto,
    @Json(name = "wscGame") val game: WscGameDto?
)



