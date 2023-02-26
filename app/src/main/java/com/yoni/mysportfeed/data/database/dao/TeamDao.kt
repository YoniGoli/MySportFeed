package com.yoni.mysportfeed.data.database.dao

import androidx.room.*
import com.yoni.mysportfeed.data.database.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAll(): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg matches: TeamEntity)

    @Delete
    fun delete(match: TeamEntity)
}