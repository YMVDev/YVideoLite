package ru.ymv.yvideolite.components.controls_rows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import ru.ymv.yvideolite.components.buttons.GoNextButton
import ru.ymv.yvideolite.components.buttons.GoPreviousButton
import ru.ymv.yvideolite.components.buttons.PlayPauseButton

@Composable
internal fun PlaybackControlsRow(
    player: Player,
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp,
    iconColor: Color = Color.White
) {
       Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GoPreviousButton(player = player, iconSize = iconSize, color = iconColor)
        PlayPauseButton(player = player, iconSize = iconSize, color = iconColor)
        GoNextButton(player = player, iconSize = iconSize, color = iconColor)
    }
}