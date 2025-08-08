package ru.ymv.yvideolite.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import kotlinx.coroutines.delay
import ru.ymv.yvideolite.utils.extensions.currentPositionAsFractionOfDuration

/**
 * Remembers and manages the player position state, including current position, duration, and slider position.
 *
 * This composable function creates a [PlayerPositionState] object that holds the current playback position,
 * duration, and slider position of an ExoPlayer [Player]. It uses a [DisposableEffect] to listen for player
 * events and update the state accordingly. It also uses a [LaunchedEffect] to continuously update the
 * position while the user is not interacting with the slider.
 *
 * The [PlayerPositionState] is designed to be used with a slider or other UI element that allows the user
 * to control the playback position.
 *
 * @param player The ExoPlayer [Player] to observe.
 * @return A [PlayerPositionState] object that holds the player's position information.
 */
@Composable
internal fun rememberPlayerPositionState(
    player: Player
): PlayerPositionState {
    val state = remember { PlayerPositionState(player = player) }

    DisposableEffect(player) {
        val listener = createPlayerListener(player, state)
        player.addListener(listener)

        onDispose {
            player.removeListener(listener)
        }
    }

    LaunchedEffect(player, state.isUserSliding) {
        if (!state.isUserSliding) {
            while (true) {
                state.currentPosition = player.currentPosition
                state.sliderPosition = player.currentPositionAsFractionOfDuration
                delay(16)
            }
        }
    }

    return state
}

/**
 * Creates a [Player.Listener] that updates the [PlayerPositionState] based on player events.
 *
 * This function creates a listener that listens for position discontinuities, timeline changes,
 * and playback state changes. When these events occur, the listener updates the corresponding
 * properties in the [PlayerPositionState] object.
 *
 * @param player The ExoPlayer [Player] to listen to.
 * @param state The [PlayerPositionState] object to update.
 * @return A [Player.Listener] instance.
 */
private fun createPlayerListener(
    player: Player,
    state: PlayerPositionState
): Player.Listener = object : Player.Listener {
    override fun onPositionDiscontinuity(
        oldPosition: Player.PositionInfo,
        newPosition: Player.PositionInfo,
        reason: Int
    ) {
        if (!state.isUserSliding) {
            state.currentPosition = player.currentPosition
        }
    }

    override fun onTimelineChanged(timeline: Timeline, reason: Int) {
        state.duration = player.duration
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        if (playbackState == Player.STATE_READY) {
            state.duration = player.duration
        }
    }
}

/**
 * Holds the state of the player position, including current position, duration, slider position, and user interaction flags.
 *
 * @param player The ExoPlayer [Player] associated with this state.
 */
class PlayerPositionState(val player: Player) {
    /**
     * The current playback position in milliseconds.
     */
    var currentPosition by mutableLongStateOf(player.currentPosition)
    /**
     * The total duration of the media in milliseconds.
     */
    var duration by mutableLongStateOf(player.duration)
    /**
     * The current playback position as a fraction of the duration (0f to 1f). This value is typically used
     * to represent the position of a thumb on a time bar or slider.
     */
    var sliderPosition by mutableFloatStateOf(player.currentPositionAsFractionOfDuration)
    /**
     * Indicates whether the user is currently sliding the slider.
     */
    var isUserSliding by mutableStateOf(false)
    /**
     * The position to which the user is sliding (in milliseconds).
     */
    var slidingPosition by mutableLongStateOf(0L)
}