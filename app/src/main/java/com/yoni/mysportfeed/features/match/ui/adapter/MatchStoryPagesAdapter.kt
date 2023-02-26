package com.yoni.mysportfeed.features.match.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoni.mysportfeed.databinding.LayoutStoryPageRowBinding
import com.yoni.mysportfeed.features.match.ui.model.MatchStoryPageRow

internal class MatchStoryPagesAdapter(
    private val onItemClickCb: StoryRowOnClickCb
): ListAdapter<MatchStoryPageRow, StoryPageRowViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPageRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = LayoutStoryPageRowBinding.inflate(
            inflater, parent, false
        )

        return StoryPageRowViewHolder(
            binding = itemBinding,
            onClickCb = onItemClickCb
        )
    }

    override fun onBindViewHolder(viewHolder: StoryPageRowViewHolder, position: Int) {
        if(position == RecyclerView.NO_POSITION) {
            return
        }

        getItem(position)?.also {
            viewHolder.bind(
                item = it
            )
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MatchStoryPageRow>() {
            override fun areItemsTheSame(
                oldItem: MatchStoryPageRow,
                newItem: MatchStoryPageRow
            ): Boolean {
                return oldItem.duration == newItem.duration
            }

            override fun areContentsTheSame(
                oldItem: MatchStoryPageRow,
                newItem: MatchStoryPageRow
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}