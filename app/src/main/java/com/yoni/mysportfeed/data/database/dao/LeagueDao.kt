package com.yoni.mysportfeed.data.database.dao

import androidx.room.*
import com.yoni.mysportfeed.data.database.entity.LeagueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {
    @Query("SELECT * FROM leagues")
    fun getAll(): Flow<List<LeagueEntity>>

    @Query("SELECT * FROM leagues WHERE id = :id")
    fun getLeague(id: Long): LeagueEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg matches: LeagueEntity)

    @Delete
    fun delete(match: LeagueEntity)
}