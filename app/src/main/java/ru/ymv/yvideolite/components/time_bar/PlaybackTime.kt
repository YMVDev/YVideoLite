package ru.ymv.yvideolite.components.time_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.ymv.yvideolite.utils.extensions.clickableWithoutRipple
import ru.ymv.yvideolite.utils.extensions.toTimeString

/**
 * Displays the playback time, allowing the user to toggle between showing the elapsed time and the remaining time.
 *
 * This composable function displays the current playback time and the total duration of the media.
 * It allows the user to tap the displayed time to toggle between showing the elapsed time and the
 * remaining time.
 *
 * @param isUserSliding A boolean indicating whether the user is currently sliding the time bar.
 * @param currentPosition The current playback position in milliseconds.
 * @param slidingPosition The position to which the user is sliding the time bar (in milliseconds).
 * @param duration The total duration of the media in milliseconds.
 */
@Composable
internal fun PlaybackTime(
    isUserSliding: Boolean,
    currentPosition: Long,
    slidingPosition: Long,
    duration: Long,
) {
    var showRemainingTime by rememberSaveable { mutableStateOf(false) }

    val position = remember(slidingPosition, currentPosition) {
        if (isUserSliding) slidingPosition else currentPosition
    }

    val timer = remember(showRemainingTime, position, duration) {
        if (showRemainingTime) duration - position else position
    }

    val text = remember(showRemainingTime, timer, duration) {
        buildString {
            if (showRemainingTime) append("-")
            append(timer.toTimeString())
            append(" / ")
            append(duration.toTimeString())
        }
    }

    Box(
        modifier = Modifier.clickableWithoutRipple { showRemainingTime = !showRemainingTime }
    ) {
        Text(text = text, color = Color.White)
    }
}