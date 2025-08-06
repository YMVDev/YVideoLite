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

class PlayerPositionState(val player: Player) {
    var currentPosition by mutableLongStateOf(player.currentPosition)
    var duration by mutableLongStateOf(player.duration)
    var sliderPosition by mutableFloatStateOf(player.currentPositionAsFractionOfDuration)
    var isUserSliding by mutableStateOf(false)
    var slidingPosition by mutableLongStateOf(0L)
}