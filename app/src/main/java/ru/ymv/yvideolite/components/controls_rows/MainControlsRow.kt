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