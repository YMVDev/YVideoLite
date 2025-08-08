package ru.ymv.yvideolite.components.buttons

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import ru.ymv.yvideolite.R

/**
 * A button that toggles between play and pause states of an ExoPlayer [Player].
 *
 * This composable function creates an IconButton that, when clicked, toggles the playback state
 * of the ExoPlayer between playing and paused. The button's icon changes dynamically based on the
 * current playback state.
 *
 * @param player The ExoPlayer [Player] to control.
 * @param modifier Modifier to be applied to the button.
 * @param color The color of the icon when the button is enabled. Defaults to [Color.Unspecified].
 * @param disabledColor The color of the icon when the button is disabled. Defaults to [Color.Unspecified].
 * @param backgroundColor The background color of the button. Defaults to [Color.Transparent].
 * @param iconSize The size of the icon. Defaults to 40.dp.
 * @param iconPlay The [ImageVector] to use for the play icon. Defaults to [Icons.Default] PlayArrow.
 * @param iconPause The [ImageVector] to use for the pause icon. Defaults to [Icons.Default] Pause.
 */
@OptIn(UnstableApi::class)
@Composable
internal fun PlayPauseButton(
    player: Player,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    disabledColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Transparent,
    iconSize: Dp = 40.dp,
    iconPlay: ImageVector = Icons.Default.PlayArrow,
    iconPause: ImageVector = Icons.Default.Pause
) {
    val state = rememberPlayPauseButtonState(player)
    val iconProperties = if (state.showPlay) {
        iconPlay to stringResource(R.string.Play)
    } else {
        iconPause to stringResource(R.string.Pause)
    }
    IconButton(
        onClick = state::onClick,
        modifier = modifier,
        colors = IconButtonDefaults.filledIconButtonColors().copy(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = disabledColor
        ),
        enabled = state.isEnabled
    ) {
        Icon(
            imageVector = iconProperties.first,
            contentDescription = iconProperties.second,
            modifier = Modifier.size(iconSize)
        )
    }
}