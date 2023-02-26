package com.yoni.mysportfeed.features.story

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import com.yoni.mysportfeed.R
import com.yoni.mysportfeed.common.loadImage
import com.yoni.mysportfeed.common.viewBinding
import com.yoni.mysportfeed.common.whenPaused
import com.yoni.mysportfeed.databinding.FragmentStoryBinding
import com.yoni.mysportfeed.features.story.extension.playItemAfterPreRoll
import com.yoni.mysportfeed.features.story.extension.trackProgress
import com.yoni.mysportfeed.features.story.ui.StoryUIState
import com.yoni.mysportfeed.features.story.ui.model.StoryItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StoryFragment: Fragment(R.layout.fragment_story) {

    private val viewModel by viewModels<StoryViewModel>()
    private val binding by viewBinding(FragmentStoryBinding::bind)

    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private var afterPreRollIndex: Int? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.also {
            afterPreRollIndex = it.getInt("pageIndex")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, minActiveState = Lifecycle.State.RESUMED)
                .onEach(this@StoryFragment::render)
                .collect()
        }

        binding.previousPage.setOnClickListener {
            player?.takeIf { it.isPlaying && it.currentMediaItemIndex > 0 }?.seekToPrevious()
        }

        binding.nextPage.setOnClickListener {
            player?.takeIf { it.isPlaying }?.seekToNext()
        }
    }

    private fun initExoPlayer(mediaItems: List<androidx.media3.common.MediaItem>) {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                binding.storyVideoPlayer.player = exoPlayer
                binding.storyVideoPlayer.useController = false

                exoPlayer.setMediaItems(mediaItems)

                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)


                exoPlayer.trackProgress(viewLifecycleOwner.lifecycleScope) {
                    binding.storyStateGroup.visibility = View.VISIBLE

                    val chunkSize = (100f / mediaItemCount.toFloat())
                    val offset =  chunkSize * currentMediaItemIndex
                    val relativePosition = currentPosition.toFloat() / duration.toFloat()
                    val storyProgress = offset + (chunkSize * relativePosition).toInt()

                    binding.storyProgress.progress = storyProgress.toInt()
                }

                exoPlayer.addListener(object: Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        super.onPlayerError(error)
                        renderErrorState()
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)

                        when(playbackState) {
                            STATE_ENDED -> closeFragment()
                        }
                    }
                })

                afterPreRollIndex?.also {
                    exoPlayer.playItemAfterPreRoll(it)
                }

                exoPlayer.prepare()
            }

        viewLifecycleOwner.whenPaused {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    private fun render(state: StoryUIState) {
        when(state) {
            StoryUIState.Error -> renderErrorState()
            StoryUIState.Loading -> renderLoadingState()
            is StoryUIState.Story -> renderStoryState(state.story)
        }
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

        closeFragment()
    }

    private fun closeFragment(){
        findNavController().popBackStack()
    }

    private fun renderStoryState(story: StoryItem) {
        initExoPlayer(story.videos.map {
            MediaItem.fromUri(it.videoUrl)
        })

        binding.gameInfo.storyThumbnail.loadImage(story.thumbnailSquare)

        binding.gameInfo.storyTitle.text = story.title
    }
}