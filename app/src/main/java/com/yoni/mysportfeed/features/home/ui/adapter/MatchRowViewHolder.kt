package com.yoni.mysportfeed.features.home.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yoni.mysportfeed.common.loadImage
import com.yoni.mysportfeed.databinding.LayoutMatchInfoBinding
import com.yoni.mysportfeed.databinding.LayoutMatchRowBinding
import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem

internal typealias OnMatchItemClick = (HomeMatchItem) -> Unit

internal class MatchRowViewHolder(
    private val binding: LayoutMatchRowBinding,
    private val onClickCb: OnMatchItemClick
): RecyclerView.ViewHolder(binding.root) {

    fun bind(match: HomeMatchItem) {
        binding.root.setOnClickListener {
            onClickCb(match)
        }

        binding.homeTeamLogo.loadImage(match.home.logo)
        binding.awayTeamLogo.loadImage(match.away.logo)

        binding.homeTeamName.text = match.home.name
        binding.awayTeamName.text = match.away.name
    }

}