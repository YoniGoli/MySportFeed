package com.yoni.mysportfeed.features.story.ui

import com.yoni.mysportfeed.features.story.ui.model.StoryItem

internal sealed class StoryUIState {
    object Loading: StoryUIState()
    object Error: StoryUIState()
    data class Story(val story: StoryItem): StoryUIState()
}
