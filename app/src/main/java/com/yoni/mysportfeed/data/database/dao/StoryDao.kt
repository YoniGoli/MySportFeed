package com.yoni.mysportfeed.data.database.dao

import androidx.room.*
import com.yoni.mysportfeed.data.database.entity.StoryEntity
import com.yoni.mysportfeed.data.database.entity.StoryPageEntity
import com.yoni.mysportfeed.data.database.entity.StoryWithPages
import com.yoni.mysportfeed.data.database.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories")
    fun getAllStories(): Flow<List<StoryWithPages>>

    @Query("SELECT * FROM stories WHERE id = :id")
    fun getStoryById(id: String): StoryWithPages?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(vararg story: StoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPage(vararg page: StoryPageEntity)

    @Delete
    fun deleteStory(story: StoryEntity)

    @Delete
    fun deletePage(page: StoryPageEntity)
}