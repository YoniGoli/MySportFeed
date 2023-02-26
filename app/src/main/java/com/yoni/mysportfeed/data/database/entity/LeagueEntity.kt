package com.yoni.mysportfeed.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class LeagueEntity (
    @PrimaryKey val id: Long,
    @ColumnInfo val name: String,
    @ColumnInfo val logo: String
)