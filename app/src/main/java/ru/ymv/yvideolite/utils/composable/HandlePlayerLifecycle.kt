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

/**
 * Handles the ExoPlayer's playback state based on the lifecycle events of a [androidx.lifecycle.LifecycleOwner].
 *
 * This composable function manages the ExoPlayer's playback state (play/pause) in response to
 * lifecycle events such as `ON_PAUSE` and `ON_RESUME`. It ensures that the player is paused when
 * the associated component (e.g., Activity or Fragment) is paused and resumes playback when the
 * component is resumed, if it was playing before.
 *
 * @param player The ExoPlayer [Player] to control.
 * @param lifecycle The current [Lifecycle.Event] of the associated component.
 * @param isPlaying A boolean indicating whether the player is currently playing.
 */
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