package com.yoni.mysportfeed.features.match.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.yoni.mysportfeed.databinding.LayoutStoryPageRowBinding
import com.yoni.mysportfeed.features.match.ui.model.MatchStoryPageRow

internal typealias StoryRowOnClickCb = (MatchStoryPageRow) -> Unit

internal class StoryPageRowViewHolder(
    private val binding: LayoutStoryPageRowBinding,
    private val onClickCb: StoryRowOnClickCb
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MatchStoryPageRow) {
        binding.root.setOnClickListener {
            onClickCb(item)
        }

        binding.storyTitle.text = item.title

        item.description?.also {
            binding.storyDescription.text = it
        }

    }

}