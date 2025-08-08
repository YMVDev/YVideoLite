package ru.ymv.yvideolite.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.media3.common.Player

/**
 * Remembers and observes the playing state of an ExoPlayer [Player], converting it into a Composable State.
 *
 * This composable function creates a state variable that reflects the current playing state
 * (isPlaying) of the provided ExoPlayer [Player]. The [Player.isPlaying] property directly returns
 * the current playing state but is not a Composable State. This function transforms it into a State
 * that can be observed within a Composable.
 *
 * It uses [DisposableEffect] to add and remove a listener to the player, ensuring that the state is
 * updated whenever the player's playing state changes. The state is also saved using [rememberSaveable]
 * to survive configuration changes.
 *
 * @param player The ExoPlayer [Player] to observe.
 * @return A boolean value indicating whether the player is currently playing.
 */
@Composable
fun rememberPlayingState(player: Player): Boolean {
    var isPlayingState by rememberSaveable { mutableStateOf(player.isPlaying) }

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                isPlayingState = isPlaying
            }
        }
        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    return isPlayingState
}