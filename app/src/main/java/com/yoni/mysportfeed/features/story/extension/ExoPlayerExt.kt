package com.yoni.mysportfeed.features.story.extension

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal fun ExoPlayer.trackProgress(
    lifecycleScope: CoroutineScope,
    interval: Long = 500L,
    cb: ExoPlayer.() -> Unit) {
    var alreadyAdded = false

    addListener(object: Player.Listener {
        override fun onIsPlayingChanged(newStateIsPlaying: Boolean) {
            if(!alreadyAdded && newStateIsPlaying) {
                alreadyAdded = true

                lifecycleScope.launch {
                    while(isActive && isPlaying) {
                        cb()
                        delay(interval)
                    }

                    alreadyAdded = false
                }
            }
        }
    })
}

internal fun ExoPlayer.playItemAfterPreRoll(itemIndex: Int) {
    addListener(object: Player.Listener {
        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            super.onMediaItemTransition(mediaItem, reason)
            seekTo(itemIndex, 0)
            removeListener(this)
        }
    })
}
