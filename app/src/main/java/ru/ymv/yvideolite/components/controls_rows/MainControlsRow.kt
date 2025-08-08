package ru.ymv.yvideolite.components.controls_rows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import ru.ymv.yvideolite.components.buttons.RepeatButton
import ru.ymv.yvideolite.components.buttons.ScaleButton
import ru.ymv.yvideolite.components.buttons.SelectButton
import ru.ymv.yvideolite.components.buttons.ShuffleButton
import ru.ymv.yvideolite.components.buttons.SpeedButton

/**
 * A row of main control buttons for an ExoPlayer [Player].
 *
 * This composable function creates a row of buttons that provide common playback controls,
 * including scaling, speed selection, shuffling, repeating, and video selection.
 *
 * @param player The ExoPlayer [Player] to control.
 * @param onSelect A callback invoked when the select button is clicked. This is typically used
 *  to allow the user to choose a new video.
 * @param onScale A callback invoked when the scale button is clicked. This is typically used to
 *  toggle between different video scaling modes.
 * @param modifier Modifier to be applied to the row.
 * @param color The color of the icons/text in the row. Defaults to [Color.White].
 * @param horizontalArrangement The horizontal arrangement of the buttons within the row.
 *  Defaults to [Arrangement.Center].
 * @param selectedSpeedColor The color to use for the selected playback speed in the [SpeedButton].
 *  Defaults to the value of [color].
 */
@Composable
internal fun MainControlsRow(
    player: Player,
    onSelect: () -> Unit = {},
    onScale: () -> Unit = {},
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    selectedSpeedColor: Color = color,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScaleButton(color = color, onClick = onScale)
        SpeedButton(player = player, color = color, selectedColor = selectedSpeedColor)
        ShuffleButton(player = player, color = color)
        RepeatButton(player = player, color = color)
        SelectButton(color = color, onClick = onSelect)
    }
}