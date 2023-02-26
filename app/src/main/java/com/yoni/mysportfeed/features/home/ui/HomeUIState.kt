package com.yoni.mysportfeed.features.home.ui

import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem

internal sealed class HomeUIState {
    object Loading: HomeUIState()
    object Error: HomeUIState()
    data class MatchesList(val matches: List<HomeMatchItem>): HomeUIState()
}