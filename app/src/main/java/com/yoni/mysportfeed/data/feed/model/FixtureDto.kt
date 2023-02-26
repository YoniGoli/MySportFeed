package com.yoni.mysportfeed.data.feed.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FixtureDto(
    val id: Long,
    val timezone: String,
    val timestamp: Long,
    val date: String,
    val status: FixtureStatusDto,
    val venue: FixtureVenueDto,
    val referee: String?
)

@JsonClass(generateAdapter = true)
data class FixtureStatusDto(
    val long: String,
    val short: String
)

@JsonClass(generateAdapter = true)
data class FixtureVenueDto(
    val id: Long,
    val name: String,
    val city: String,
)