package com.yoni.mysportfeed.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EnrichedMatch(
    @Embedded val match: MatchEntity,

    @Relation(parentColumn = "homeTeam", entityColumn = "id")
    val homeTeam: TeamEntity,

    @Relation(parentColumn = "awayTeam", entityColumn = "id")
    val awayTeam: TeamEntity,

    @Relation(parentColumn = "league", entityColumn = "id")
    val league: LeagueEntity,

    @Relation(parentColumn = "venue", entityColumn = "id")
    val venue: VenueEntity,

    @Relation(parentColumn = "id", entityColumn = "matchId")
    val story: StoryEntity?,
)