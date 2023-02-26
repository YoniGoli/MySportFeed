package com.yoni.mysportfeed.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "story_pages",
    foreignKeys = [
        ForeignKey(
            entity = StoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("storyId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StoryPageEntity(
    @PrimaryKey val id: String,
    @ColumnInfo val storyId: String,
    @ColumnInfo val duration: Int,
    @ColumnInfo val videoUrl: String,
    @ColumnInfo val title: String?,
    @ColumnInfo val awayScore: Int?,
    @ColumnInfo val homeScore: Int?,
    @ColumnInfo val eventType: String?,
    @ColumnInfo val actionType: String?,
    @ColumnInfo val gameClock: String?,
    @ColumnInfo val rating: Int,
    @ColumnInfo val period: String?,
)
