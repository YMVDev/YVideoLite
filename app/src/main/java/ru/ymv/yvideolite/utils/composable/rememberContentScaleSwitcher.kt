package ru.ymv.yvideolite.utils.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale

@Composable
fun rememberContentScaleSwitcher(): Pair<ContentScale, () -> Unit> {
    val scales = remember {
        listOf(
            ContentScale.Fit,
            ContentScale.Crop,
            ContentScale.Inside,
            ContentScale.FillWidth,
            ContentScale.FillHeight,
            ContentScale.FillBounds,
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