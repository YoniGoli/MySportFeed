package com.yoni.mysportfeed.features.match

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yoni.mysportfeed.features.match.data.IMatchRepository
import com.yoni.mysportfeed.features.match.ui.MatchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MatchViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val repository: IMatchRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<MatchUIState> = MutableStateFlow(MatchUIState.Loading)
    val uiState: StateFlow<MatchUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                state.get<String>("matchId")?.let {
                    loadMatch(it)
                } ?: MatchUIState.Error
            )
        }
    }

    private suspend fun loadMatch(matchId: String): MatchUIState.Item? =
        repository.getMatch(matchId)?.let {
            MatchUIState.Item(it)
        }

}