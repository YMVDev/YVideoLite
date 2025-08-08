package ru.ymv.yvideolite.utils.extensions

import android.annotation.SuppressLint

/**
 * Converts a duration in milliseconds to a formatted time string (HH:MM:SS or MM:SS).
 *
 * This function takes a [Long] representing a duration in milliseconds and converts it into a human-readable
 * time string. If the duration is greater than or equal to one hour, the format is HH:MM:SS.
 * Otherwise, the format is MM:SS. The function also ensures that the input duration is not negative by coercing
 * it to a minimum value of 0.
 *
 * The `@SuppressLint("DefaultLocale")` annotation is used because `String.format` uses the default locale,
 * which may not be appropriate for all use cases. Consider using a specific locale if consistent formatting
 * across different devices and regions is required.
 *
 * @return A formatted time string representing the duration.
 */
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