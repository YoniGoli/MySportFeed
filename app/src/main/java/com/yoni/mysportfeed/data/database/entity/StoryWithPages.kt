package com.yoni.mysportfeed.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class  StoryWithPages (
    @Embedded val story: StoryEntity,

    @Relation(parentColumn = "id", entityColumn = "storyId")
    val pages: List<StoryPageEntity>,
)