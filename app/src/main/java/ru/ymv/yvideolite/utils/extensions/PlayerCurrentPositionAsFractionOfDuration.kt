package ru.ymv.yvideolite.utils.extensions

import androidx.media3.common.Player

val Player.currentPositionAsFractionOfDuration: Float
    get() = if (duration > 0) currentPosition.toFloat() / duration.toFloat() else 0f