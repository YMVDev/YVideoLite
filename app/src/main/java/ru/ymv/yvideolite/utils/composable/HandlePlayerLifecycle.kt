package ru.ymv.yvideolite.utils.composable

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi

@OptIn(UnstableApi::class)
@Composable
fun HandlePlayerLifecycle(
    player: Player,
    lifecycle: Lifecycle.Event,
    isPlaying: Boolean,
) {
    var wasPlaying by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(lifecycle) {
        when (lifecycle) {
            Lifecycle.Event.ON_PAUSE -> {
                wasPlaying = isPlaying
                if (isPlaying) {
                    player.pause()
                }
                Log.d(
                    "mylog",
                    "ON_PAUSE\nwasPlaying = $wasPlaying"
                )
            }

            Lifecycle.Event.ON_RESUME -> {
                Log.d(
                    "mylog",
                    "ON_RESUME\nwasPlaying = $wasPlaying"
                )
                if (wasPlaying && !isPlaying) {
                    player.play()
                }
            }

            else -> {}
        }
    }
}