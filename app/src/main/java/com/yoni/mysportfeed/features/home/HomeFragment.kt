package com.yoni.mysportfeed.features.home

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
import com.google.android.material.snackbar.Snackbar
import com.yoni.mysportfeed.R
import com.yoni.mysportfeed.common.viewBinding
import com.yoni.mysportfeed.databinding.FragmentHomeBinding
import com.yoni.mysportfeed.features.home.ui.HomeUIState
import com.yoni.mysportfeed.features.home.ui.adapter.MatchesAdapter
import com.yoni.mysportfeed.features.home.ui.model.HomeMatchItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val adapter = MatchesAdapter(this::onItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, minActiveState = Lifecycle.State.RESUMED)
                .onEach(this@HomeFragment::render)
                .collect()
        }

        binding.matchesList.adapter = adapter
        binding.matchesList.layoutManager = LinearLayoutManager(requireContext())

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.matchesList.addItemDecoration(decoration)
    }

    private fun render(state: HomeUIState) {
        when(state) {
            HomeUIState.Error -> renderErrorState()
            HomeUIState.Loading -> renderLoadingState()
            is HomeUIState.MatchesList -> renderMatchesList(state.matches)
        }
    }

    private fun renderMatchesList(matches: List<HomeMatchItem>) {
        adapter.submitList(matches)
    }

    private fun renderLoadingState() {

    }

    private fun renderErrorState() {
        Snackbar
            .make(
                binding.root,
                R.string.something_went_wrong_title,
                Snackbar.LENGTH_SHORT
            )
            .show()
    }

    private fun onItemClicked(match: HomeMatchItem) {
        findNavController().navigate(R.id.navigation_match, bundleOf(
            "matchId" to match.id
        ))
    }

}