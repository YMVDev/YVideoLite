package ru.ymv.yvideolite.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Converts a Float value to a [Dp] (density-independent pixel) value.
 *
 * This extension function provides a convenient way to convert a `Float` representing a
 * pixel value to a `Dp` value, taking into account the current screen density.  It uses
 * the [LocalDensity] to perform the conversion.
 *
 * @return A [Dp] value representing the equivalent density-independent pixel value.
 */
@Composable
fun Float.toDp(): Dp = with(LocalDensity.current) { this@toDp.toDp() }