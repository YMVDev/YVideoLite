package ru.ymv.yvideolite.components.buttons

import android.view.Gravity
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.rememberPlaybackSpeedState
import ru.ymv.yvideolite.components.dialogs.SpeedSelectionDialog
import ru.ymv.yvideolite.components.text.AutoSizeText

@OptIn(UnstableApi::class)
@Composable
internal fun SpeedButton(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    selectedColor: Color = color,
    speedVariants: List<Float> = listOf(0.5f, 1f, 2f, 3f),
) {
    val state = rememberPlaybackSpeedState(player)
    var isVisible by rememberSaveable { mutableStateOf(false) }
    TextButton(
        onClick = { isVisible = !isVisible },
        modifier = modifier.size(77.dp, 50.dp),
        enabled = state.isEnabled
    ) {
        AutoSizeText(
            text = "%.2fx".format(state.playbackSpeed),
            color = color,
        )
    }
    if (isVisible) {
        SpeedSelectionDialog(
            gravity = Gravity.TOP,
            modifier = Modifier
                .safeDrawingPadding()
                .widthIn(max = 500.dp)
                .padding(8.dp),
            currentSpeed = state.playbackSpeed,
            speedVariants = speedVariants,
            speedRange = 0.25f..3f,
            onDismiss = { isVisible = false },
            onSelect = state::updatePlaybackSpeed,
            textColor = color,
            selectedColor = selectedColor,
            backgroundColor = Color.DarkGray,
            usePlatformDefaultWidth = false
        )
    }
}