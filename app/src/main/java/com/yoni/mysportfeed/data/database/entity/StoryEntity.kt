package com.yoni.mysportfeed.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "stories",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = MatchEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("matchId"),
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class StoryEntity(
    @PrimaryKey val id: String,
    val matchId: String,
    val title: String,
    val thumbnail: String,
    val thumbnailSquare: String,
    val publishDate: String,
)