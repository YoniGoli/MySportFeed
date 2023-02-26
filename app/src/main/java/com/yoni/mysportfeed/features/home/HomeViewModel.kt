package com.yoni.mysportfeed.features.home

import androidx.lifecycle.ViewModel
import com.yoni.mysportfeed.features.home.data.IHomeRepository
import com.yoni.mysportfeed.features.home.ui.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    repository: IHomeRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)
    val uiState: Flow<HomeUIState> = _uiState.asStateFlow()
        .combine(repository.getAllMatches()) { _, matchesList ->
            HomeUIState.MatchesList(matchesList)
        }

}