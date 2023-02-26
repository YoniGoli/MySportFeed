package com.yoni.mysportfeed.features.match.data

import com.yoni.mysportfeed.features.match.ui.model.MatchItem

internal interface IMatchRepository {
    suspend fun getMatch(id: String): MatchItem?
}