package com.yoni.mysportfeed.data.database.dao

import androidx.room.*
import com.yoni.mysportfeed.data.database.entity.EnrichedMatch
import com.yoni.mysportfeed.data.database.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchesDao {
    @Transaction
    @Query("SELECT * FROM matches ORDER BY timestamp")
    fun getAll(): Flow<List<EnrichedMatch>>

    @Query("SELECT COUNT(*) FROM matches")
    fun totalMatches(): Int

    @Query("SELECT COUNT(*) FROM matches")
    fun getCount(): Int

    @Transaction
    @Query("SELECT * FROM matches WHERE id = :id")
    fun getMatch(id: String): EnrichedMatch?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(match: MatchEntity)

    @Delete
    fun delete(match: MatchEntity)
}