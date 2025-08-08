package ru.ymv.yvideolite.utils.extensions

import androidx.media3.common.Player

/**
 * Calculates the current playback position of a [Player] as a fraction of its total duration.
 *
 * This property provides a normalized value (between 0f and 1f) representing the current playback
 * position relative to the total duration of the media. If the duration is 0 or not available, the
 * property returns 0f.
 *
 * @return A Float value between 0f and 1f representing the current position as a fraction of the duration.
 *         Returns 0f if the duration is 0 or not available.
 */
val Player.currentPositionAsFractionOfDuration: Float
    get() = if (duration > 0) currentPosition.toFloat() / duration.toFloat() else 0f