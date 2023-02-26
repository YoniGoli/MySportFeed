package com.yoni.mysportfeed.features.home.data

import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem
import kotlinx.coroutines.flow.Flow

internal interface IHomeRepository {
    fun getAllMatches(): Flow<List<HomeMatchItem>>
}