package com.yoni.mysportfeed.features.story.ui.model

internal data class StoryItem(
    val id: String,
    val title: String,
    val thumbnailSquare: String,
    val videos: List<PageItem>
)

internal data class PageItem(
    val videoUrl: String
)