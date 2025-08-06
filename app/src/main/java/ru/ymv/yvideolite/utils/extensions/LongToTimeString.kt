package ru.ymv.yvideolite.utils.extensions

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
fun Long.toTimeString(): String {
    val duration = this.coerceAtLeast(0)
    val totalSeconds = (duration / 1000).toInt()
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return when {
        hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
        else -> String.format("%02d:%02d", minutes, seconds)
    }
}