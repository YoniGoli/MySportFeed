package com.yoni.mysportfeed.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoni.mysportfeed.data.database.dao.*
import com.yoni.mysportfeed.data.database.dao.StoryDao
import com.yoni.mysportfeed.data.database.entity.*

@Database(
    entities = [
        LeagueEntity::class,
        MatchEntity::class,
        TeamEntity::class,
        VenueEntity::class,
        StoryEntity::class,
        StoryPageEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchesDao(): MatchesDao
    abstract fun leagueDao(): LeagueDao
    abstract fun teamDao(): TeamDao
    abstract fun venueDao(): VenueDao
    abstract fun storyDao(): StoryDao
}