package com.yoni.mysportfeed.features.story.data

import com.yoni.mysportfeed.features.story.ui.model.StoryItem

internal interface IStoryRepository {
    suspend fun getStory(id: String): StoryItem?
}