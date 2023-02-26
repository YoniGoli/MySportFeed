package com.yoni.mysportfeed.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "matches",
    foreignKeys = [
        ForeignKey(
            entity = TeamEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("homeTeam"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = LeagueEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("league"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = VenueEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("venue"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class MatchEntity (
    @PrimaryKey val id: String,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val homeTeam: Long,
    @ColumnInfo val awayTeam: Long,
    @ColumnInfo val league: Long,
    @ColumnInfo val season: Int,
    @ColumnInfo val leagueRound: String,
    @ColumnInfo val venue: Long,
    @ColumnInfo val status: String,
    @ColumnInfo val referee: String?,
)