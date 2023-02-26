package com.yoni.mysportfeed.features.match

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import com.yoni.mysportfeed.R
import com.yoni.mysportfeed.common.loadImage
import com.yoni.mysportfeed.common.viewBinding
import com.yoni.mysportfeed.databinding.FragmentMatchBinding
import com.yoni.mysportfeed.features.match.ui.MatchUIState
import com.yoni.mysportfeed.features.match.ui.adapter.MatchStoryPagesAdapter
import com.yoni.mysportfeed.features.match.ui.model.MatchItem
import com.yoni.mysportfeed.features.match.ui.model.MatchStoryPageRow
import com.yoni.mysportfeed.features.story.ui.StoryUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchFragment: Fragment(R.layout.fragment_match) {
    private val viewModel by viewModels<MatchViewModel>()
    private val binding by viewBinding(FragmentMatchBinding::bind)

    private val adapter = MatchStoryPagesAdapter(this::onPageRowClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(
                    viewLifecycleOwner.lifecycle, minActiveState =
                    Lifecycle.State.RESUMED
                )
                .onEach(this@MatchFragment::render)
                .collect()
        }

        binding.pagesList.adapter = adapter
        binding.pagesList.layoutManager = LinearLayoutManager(requireContext())

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.pagesList.addItemDecoration(decoration)
    }

    private fun onPageRowClicked(page: MatchStoryPageRow) {
        findNavController().navigate(R.id.navigation_story, bundleOf(
            "storyId" to page.storyId,
            "pageIndex" to page.index
        ))
    }

    private fun render(state: MatchUIState) {
        when(state) {
            MatchUIState.Error -> renderErrorState()
            MatchUIState.Loading -> renderLoadingState()
            is MatchUIState.Item -> renderMatchItem(state.match)
        }
    }

    private fun renderLoadingState() {

    }

    private fun renderErrorState() {
        Snackbar
            .make(
                binding.root,
                R.string.no_data,
                Snackbar.LENGTH_SHORT
            )
            .show()

        closeFragment()
    }

    private fun closeFragment(){
        findNavController().popBackStack()
    }

    private fun renderMatchItem(match: MatchItem) {
        binding.matchInfo.homeTeamLogo.loadImage(match.home.logo)
        binding.matchInfo.awayTeamLogo.loadImage(match.away.logo)

        binding.matchInfo.homeTeamName.text = match.home.name
        binding.matchInfo.awayTeamName.text = match.away.name

        adapter.submitList(match.pages)
    }
}