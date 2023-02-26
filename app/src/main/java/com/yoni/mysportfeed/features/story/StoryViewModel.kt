package com.yoni.mysportfeed.features.story

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoni.mysportfeed.features.story.data.IStoryRepository
import com.yoni.mysportfeed.features.story.ui.StoryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StoryViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val repository: IStoryRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<StoryUIState> = MutableStateFlow(StoryUIState.Loading)
    val uiState: StateFlow<StoryUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                state.get<String>("storyId")?.let {
                    loadStory(it)
                } ?: StoryUIState.Error
            )
        }
    }

    private suspend fun loadStory(storyId: String): StoryUIState.Story? =
        repository.getStory(storyId)?.let {
            StoryUIState.Story(it)
        }
}