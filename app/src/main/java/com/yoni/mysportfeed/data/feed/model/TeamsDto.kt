package com.yoni.mysportfeed.data.feed.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamsDto(
    val away: TeamDto,
    val home: TeamDto
)

@JsonClass(generateAdapter = true)
data class TeamDto(
    val id: Long,
    val name: String,
    val logo: String,
)