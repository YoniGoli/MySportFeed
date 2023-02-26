package com.yoni.mysportfeed.features.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoni.mysportfeed.databinding.LayoutMatchInfoBinding
import com.yoni.mysportfeed.databinding.LayoutMatchRowBinding
import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem

internal class MatchesAdapter(
    private val onItemClickCb: OnMatchItemClick
):  ListAdapter<HomeMatchItem, MatchRowViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = LayoutMatchRowBinding.inflate(
            inflater, parent, false
        )

        return MatchRowViewHolder(
            binding = itemBinding,
            onClickCb = onItemClickCb
        )
    }

    override fun onBindViewHolder(viewHolder: MatchRowViewHolder, position: Int) {
        if(position == RecyclerView.NO_POSITION) {
            return
        }

        getItem(position)?.also {
            viewHolder.bind(
                match = it
            )
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomeMatchItem>() {
            override fun areItemsTheSame(
                oldItem: HomeMatchItem,
                newItem: HomeMatchItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: HomeMatchItem,
                newItem: HomeMatchItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}