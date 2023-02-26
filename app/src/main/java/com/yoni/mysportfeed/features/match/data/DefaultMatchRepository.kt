package com.yoni.mysportfeed.features.match.data

import com.yoni.mysportfeed.data.database.entity.EnrichedMatch
import com.yoni.mysportfeed.data.database.entity.StoryWithPages
import com.yoni.mysportfeed.data.database.entity.TeamEntity
import com.yoni.mysportfeed.data.feed.IFeedRepository
import com.yoni.mysportfeed.features.match.ui.model.MatchItem
import com.yoni.mysportfeed.features.match.ui.model.MatchStoryPageRow
import com.yoni.mysportfeed.features.match.ui.model.MatchTeam

internal class DefaultMatchRepository(
    private val feedRepository: IFeedRepository
): IMatchRepository {

    override suspend fun getMatch(id: String) = feedRepository.getMatchById(id)?.toUiItem()

    private suspend fun EnrichedMatch.toUiItem(): MatchItem? {
        val story = getStoryWithPages()

        return story?.let { matchStory ->
            MatchItem(
                title = matchStory.story.title,
                home = homeTeam.toUiItem(),
                away = awayTeam.toUiItem(),
                pages = matchStory.toPageRows()
            )
        }

    }

    private fun TeamEntity.toUiItem(): MatchTeam {
        return MatchTeam(
            name = name,
            logo = logo,
        )
    }

    private fun StoryWithPages.toPageRows(): List<MatchStoryPageRow> {
        return pages.sortedBy { it.gameClock }.mapIndexedNotNull { index, pageInfo ->
            pageInfo.title?.let { pageTitle ->
                MatchStoryPageRow(
                    storyId = story.id,
                    index = index,
                    title = pageTitle,
                    duration = pageInfo.duration,
                    action = pageInfo.actionType,
                    description = pageInfo.gameClock
                )
            }
        }
    }

    private suspend fun EnrichedMatch.getStoryWithPages(): StoryWithPages? {
        return story?.id?.let {
            feedRepository.getStoryById(it)
        }
    }

}