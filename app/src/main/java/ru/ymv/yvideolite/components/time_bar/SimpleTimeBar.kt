package ru.ymv.yvideolite.components.time_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import ru.ymv.yvideolite.components.slider.HorizontalBoxSlider
import ru.ymv.yvideolite.utils.composable.rememberPlayerPositionState

@Composable
internal fun SimpleTimeBar(
    player: Player,
    modifier: Modifier = Modifier
) {
    val state = rememberPlayerPositionState(player)
    Column(modifier = modifier) {
        PlaybackTime(
            isUserSliding = state.isUserSliding,
            currentPosition = state.currentPosition,
            duration = state.duration,
            slidingPosition = state.slidingPosition
        )
        HorizontalBoxSlider(
            value = state.sliderPosition,
            onValueChange = { newValue ->
                state.isUserSliding = true
                state.sliderPosition = newValue
                state.slidingPosition = (newValue * state.duration).toLong()
            },
            onValueChangeFinished = {
                player.seekTo(state.slidingPosition)
                state.isUserSliding = false
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}