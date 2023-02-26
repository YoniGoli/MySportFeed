package com.yoni.mysportfeed.data.feed.util.extension

import com.yoni.mysportfeed.data.database.entity.*
import com.yoni.mysportfeed.data.feed.model.*

internal fun FeedItemDto.toEntity(
    homeTeamId: Long,
    awayTeamId: Long,
    leagueId: Long,
    venueId: Long
): MatchEntity {
    return MatchEntity(
        id = id,
        timestamp = fixture.timestamp,
        homeTeam = homeTeamId,
        awayTeam = awayTeamId,
        league = leagueId,
        leagueRound = league.round,
        season = league.season,
        venue = venueId,
        status = fixture.status.long,
        referee = fixture.referee
    )
}

internal fun TeamDto.toEntity(): TeamEntity {
    return TeamEntity(
        id = id,
        name = name,
        logo = logo
    )
}

internal fun LeagueDto.toEntity(): LeagueEntity {
    return LeagueEntity(
        id = id,
        name = name,
        logo = logo
    )
}

internal fun FixtureVenueDto.toEntity(): VenueEntity {
    return VenueEntity(
        id = id,
        name = name,
        city = city
    )
}

internal fun WscGamePrimeStoryDto.toEntity(matchId: String): StoryEntity {
    return StoryEntity(
        id = id,
        matchId = matchId,
        title = title,
        publishDate = publishDate,
        thumbnail = thumbnail,
        thumbnailSquare = thumbnailSquare
    )
}

internal fun WscGameStoryPageDto.toEntity(storyId: String): StoryPageEntity {
    return StoryPageEntity(
        id = id,
        storyId = storyId,
        duration = duration,
        videoUrl = videoUrl,
        title = title,
        awayScore = awayScore,
        homeScore = homeScore,
        eventType = eventType,
        actionType = actionType,
        gameClock = gameClock,
        rating = rating,
        period = period
    )
}