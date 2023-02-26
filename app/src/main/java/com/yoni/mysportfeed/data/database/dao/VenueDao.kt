package com.yoni.mysportfeed.data.database.dao

import androidx.room.*
import com.yoni.mysportfeed.data.database.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Query("SELECT * FROM venues")
    fun getAll(): Flow<List<VenueEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg matches: VenueEntity)

    @Delete
    fun delete(match: VenueEntity)
}