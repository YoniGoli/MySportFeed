package com.yoni.mysportfeed.features.home.data

import com.yoni.mysportfeed.data.database.entity.TeamEntity
import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem
import com.yoni.mysportfeed.features.home.ui.model.HomeMatchTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DefaultHomeRepository(
    private val feedRepository: IFeedRepository
): IHomeRepository {

    override fun getAllMatches(): Flow<List<HomeMatchItem>> {
        return feedRepository.getAllMatches()
            .map { match ->
                match.sortedBy { it.story == null }
                    .map {
                        HomeMatchItem(
                            id = it.match.id,
                            home = it.homeTeam.toUiItem(),
                            away = it.awayTeam.toUiItem()
                        )
                    }
            }

    }

    private fun TeamEntity.toUiItem(): HomeMatchTeam {
        return HomeMatchTeam(
            name = name,
            logo = logo,
        )
    }

}