package com.yoni.mysportfeed.features.home.ui.model

data class HomeMatchItem(
    val id: String,
    val home: HomeMatchTeam,
    val away: HomeMatchTeam
)

data class HomeMatchTeam(
    val name: String,
    val logo: String
)