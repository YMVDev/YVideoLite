package ru.ymv.yvideolite.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale

/**
 * Remembers and provides a mechanism to switch between different [ContentScale] values.
 *
 * This composable function creates a state that cycles through a predefined list of [ContentScale]
 * values. It returns a [Pair] containing the current [ContentScale] and a function to switch to the
 * next [ContentScale] in the list. The current index is saved using [rememberSaveable] to survive
 * configuration changes.
 *
 * @return A [Pair] where the first element is the current [ContentScale] and the second element is a
 *  function that switches to the next [ContentScale] in the list.
 */
@Composable
fun rememberContentScaleSwitcher(): Pair<ContentScale, () -> Unit> {
    val scales = remember {
        listOf(
            ContentScale.Fit,
            ContentScale.FillWidth,
            ContentScale.FillHeight,
            ContentScale.FillBounds,
            ContentScale.Crop,
            ContentScale.Inside,
            ContentScale.None
        )
    }

    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentScale = scales[currentIndex]
    val switchToNextScale = {
        currentIndex = (currentIndex + 1) % scales.size
    }
    return Pair(currentScale, switchToNextScale)
}