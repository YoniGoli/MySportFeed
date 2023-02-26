package com.yoni.mysportfeed.features.match.ui.model

data class MatchItem(
    val title: String,
    val home: MatchTeam,
    val away: MatchTeam,
    val pages: List<MatchStoryPageRow>
)

data class MatchTeam(
    val name: String,
    val logo: String
)

data class MatchStoryPageRow(
    val storyId: String,
    val index: Int,
    val duration: Int,
    val title: String,
    val action: String?,
    val description: String?
)