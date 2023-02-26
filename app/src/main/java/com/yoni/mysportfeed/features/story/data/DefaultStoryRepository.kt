package com.yoni.mysportfeed.features.story.data

import com.yoni.mysportfeed.data.database.entity.StoryWithPages
import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.story.ui.model.PageItem
import com.yoni.mysportfeed.features.story.ui.model.StoryItem

internal class DefaultStoryRepository(
    private val feedRepository: IFeedRepository
): IStoryRepository {

    override suspend fun getStory(storyId: String): StoryItem? {
        return feedRepository.getStoryById(storyId)?.let {
            it.toStoryItem()
        }
    }

    private fun StoryWithPages.toStoryItem() = StoryItem(
        id = story.id,
        title = story.title,
        thumbnailSquare = story.thumbnailSquare,
        videos = pages.map {
            PageItem(
                videoUrl = it.videoUrl
            )
        }
    )
}