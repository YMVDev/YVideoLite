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