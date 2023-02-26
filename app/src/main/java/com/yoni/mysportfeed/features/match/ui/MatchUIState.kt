package com.yoni.mysportfeed.features.match.ui

import com.yoni.mysportfeed.features.match.ui.model.MatchItem

internal sealed class MatchUIState {
    object Loading: MatchUIState()
    object Error: MatchUIState()
    data class Item(val match: MatchItem): MatchUIState()
}